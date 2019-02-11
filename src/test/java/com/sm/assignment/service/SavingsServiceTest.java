package com.sm.assignment.service;

import com.sm.assignment.dao.CurrentPricesDao;
import com.sm.assignment.dao.HistoricalPricesDao;
import com.sm.assignment.exception.ProcessingException;
import com.sm.assignment.pojo.Fuel;
import com.sm.assignment.pojo.HistoricalPrice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SavingsServiceTest {
    @Mock
    private HistoricalPricesDao historicalPricesDao;
    @Mock
    private CurrentPricesDao currentPricesDao;

    @InjectMocks
    private SavingsServiceImpl savingsServiceImpl;

    @Test
    public void testGetSavingsWithLowerHistoricPrice() {

        String friday = "08/02/2019";
        Fuel petrol = Fuel.PETROL;

        HistoricalPrice historicalPrice = new HistoricalPrice();
        historicalPrice.setUnleadedDieselPrice(new BigDecimal(110));
        historicalPrice.setUnleadedPetrolPrice(new BigDecimal(105));

        when(historicalPricesDao.getHistoricPrice(eq("11/02/2019"))).thenReturn(historicalPrice);

        when(currentPricesDao.getTodaysPrice(eq(petrol))).thenReturn(new BigDecimal(120));

        String message = savingsServiceImpl.getSavings(friday, 100d, 10d, Fuel.PETROL);

        assertEquals("correct result not returned for lower price", "Today's journey is costlier by 6.82 pounds", message);

    }

    @Test
    public void testGetSavingsWithSameHistoricPrice() {

        String friday = "08/02/2019";
        Fuel petrol = Fuel.PETROL;

        HistoricalPrice historicalPrice = new HistoricalPrice();
        historicalPrice.setUnleadedDieselPrice(new BigDecimal(110));
        historicalPrice.setUnleadedPetrolPrice(new BigDecimal(105));

        when(historicalPricesDao.getHistoricPrice(eq("11/02/2019"))).thenReturn(historicalPrice);

        when(currentPricesDao.getTodaysPrice(eq(petrol))).thenReturn(new BigDecimal(105));

        String message = savingsServiceImpl.getSavings(friday, 100d, 10d, Fuel.PETROL);

        assertEquals("correct result not returned for similar price", "Today's journey costs same as cost on the specified date", message);

    }


    @Test
    public void testGetSavingsWithHigherHistoricPrice() {

        String friday = "08/02/2019";
        Fuel petrol = Fuel.PETROL;

        HistoricalPrice historicalPrice = new HistoricalPrice();
        historicalPrice.setUnleadedDieselPrice(new BigDecimal(110));
        historicalPrice.setUnleadedPetrolPrice(new BigDecimal(120));

        when(historicalPricesDao.getHistoricPrice(eq("11/02/2019"))).thenReturn(historicalPrice);

        when(currentPricesDao.getTodaysPrice(eq(petrol))).thenReturn(new BigDecimal(110));

        String message = savingsServiceImpl.getSavings(friday, 100d, 10d, Fuel.PETROL);

        assertEquals("correct result not returned for lower price", "Today's journey is cheaper by 4.55 pounds", message);

    }

    @Test(expected=ProcessingException.class)
    public void testGetSavingsWithNoHistoricPrice() {

        String friday = "08/02/2019";
        Fuel petrol = Fuel.PETROL;

        HistoricalPrice historicalPrice = new HistoricalPrice();
        historicalPrice.setUnleadedDieselPrice(new BigDecimal(110));
        historicalPrice.setUnleadedPetrolPrice(new BigDecimal(120));

        when(currentPricesDao.getTodaysPrice(eq(petrol))).thenReturn(new BigDecimal(110));
        when(historicalPricesDao.getHistoricPrice(eq("11/02/2019"))).thenReturn(null);

        savingsServiceImpl.getSavings(friday, 100d, 10d, Fuel.PETROL);

        fail("Expected exception not raised");
    }


}
