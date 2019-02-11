package com.sm.assignment.util;

import com.sm.assignment.exception.ProcessingException;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class AssignmentUtil {

    public static BigDecimal GALLONS_TO_LITERS = new BigDecimal("4.54609");

    public static LocalDate getNearestMonday(LocalDate historicDate) {
        DayOfWeek dayOfWeek =  historicDate.getDayOfWeek();
        switch(dayOfWeek) {
            case MONDAY: return historicDate;
            case TUESDAY: return historicDate.minusDays(1);
            case WEDNESDAY: return historicDate.minusDays(2);
            case THURSDAY: return historicDate.minusDays(3);
            case FRIDAY: return historicDate.plusDays(3);
            case SATURDAY: return historicDate.plusDays(2);
            case SUNDAY: return historicDate.plusDays(1);
        }
        throw new ProcessingException("Invalid date");
    }

    public static BigDecimal getLitres(Double gallons) {
        return GALLONS_TO_LITERS.multiply(new BigDecimal(gallons));
    }

}
