package com.sm.assignment.service;

import com.sm.assignment.dao.CurrentPricesDao;
import com.sm.assignment.dao.HistoricalPricesDao;
import com.sm.assignment.exception.ProcessingException;
import com.sm.assignment.pojo.Fuel;
import com.sm.assignment.pojo.HistoricalPrice;
import com.sm.assignment.util.AssignmentUtil;
import com.sm.assignment.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class SavingsServiceImpl implements SavingsService {

    private static String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";

    private static String POUNDS = " pounds";
    private static String CHEAPER_MESSAGE_PREFIX = "Today's journey is cheaper by ";
    private static String COSTLIER_MESSAGE_PREFIX = "Today's journey is costlier by ";
    private static String SAME_COST_MESSAGE_PREFIX = "Today's journey costs same as cost on the specified date";

    private static BigDecimal ONE_HUNDRED = new BigDecimal(100);

    @Autowired
    private HistoricalPricesDao historicalPricesDao;

    @Autowired
    private CurrentPricesDao currentPrices;

    public SavingsServiceImpl() {

    }

    public String getSavings(String historicDateStr, Double mileage, Double mpg, Fuel fuelType) {


        Logger.log("Getting savings for " + historicDateStr);
        //Calculate fuel required in liters

        BigDecimal fuelRequiredInLiters = AssignmentUtil.getLitres(mileage/mpg);

        Logger.log("Fuel required in litres " + fuelRequiredInLiters.toString());

        //Calculate current expense
        BigDecimal currentExpense = currentPrices.getTodaysPrice(fuelType).multiply(fuelRequiredInLiters);

        Logger.log("Current expense in pence " + currentExpense.toString());

        //Calculate current expense
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_DD_MM_YYYY);
        LocalDate historicDate = LocalDate.parse(historicDateStr, formatter);
        LocalDate nearestMondayToHistoricDate = AssignmentUtil.getNearestMonday(historicDate);

        Logger.log("Nearest Monday is " + nearestMondayToHistoricDate.format(formatter));

        HistoricalPrice historicalPrice = historicalPricesDao.getHistoricPrice(nearestMondayToHistoricDate.format(formatter));

        BigDecimal historicExpense = null;

        if (historicalPrice != null) {
            historicExpense =  historicalPrice.getPrice(fuelType).multiply(fuelRequiredInLiters);
        } else {
            throw new ProcessingException("Historic price is not available for the specified date");
        }

        Logger.log("Historic expense is " + historicExpense.toString());

        switch(currentExpense.compareTo(historicExpense)) {
            case -1 : return String.format("%s%s%s", CHEAPER_MESSAGE_PREFIX,
                    historicExpense.subtract(currentExpense).divide(ONE_HUNDRED).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
                    POUNDS);
            case 0 : return SAME_COST_MESSAGE_PREFIX;
            case 1 : return String.format("%s%s%s",COSTLIER_MESSAGE_PREFIX,
                    currentExpense.subtract(historicExpense).divide(ONE_HUNDRED).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
                    POUNDS);
        }

        throw new ProcessingException("Failed to calculate the difference in expenses");
    }
}
