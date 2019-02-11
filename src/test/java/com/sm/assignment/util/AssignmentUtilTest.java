package com.sm.assignment.util;

import static org.junit.Assert.*;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class AssignmentUtilTest {

    @Test
    public void testGetNearestMondayFromSunday() {
        LocalDate sunday = LocalDate.of(2019, 2, 10);

        LocalDate result = AssignmentUtil.getNearestMonday(sunday);

        assertEquals("Nearest Monday not correct for a Sunday", DayOfWeek.MONDAY, result.getDayOfWeek());
        assertEquals("Incorrect day of the month", 11, result.getDayOfMonth());
    }

    @Test
    public void testGetNearestMondayFromTuesday() {
        LocalDate tuesday = LocalDate.of(2019, 2, 12);

        LocalDate result = AssignmentUtil.getNearestMonday(tuesday);

        assertEquals("Nearest Monday not correct for a Tuesday", DayOfWeek.MONDAY, result.getDayOfWeek());
        assertEquals("Incorrect day of the month", 11, result.getDayOfMonth());
    }

    @Test
    public void testGetNearestMondayFromWednesday() {
        LocalDate wednesday = LocalDate.of(2019, 2, 13);

        LocalDate result = AssignmentUtil.getNearestMonday(wednesday);

        assertEquals("Nearest Monday not correct for a Wednesday", DayOfWeek.MONDAY, result.getDayOfWeek());
        assertEquals("Incorrect day of the month", 11, result.getDayOfMonth());
    }

    @Test
    public void testGetNearestMondayFromThursday() {
        LocalDate thursday = LocalDate.of(2019, 2, 14);

        LocalDate result = AssignmentUtil.getNearestMonday(thursday);

        assertEquals("Nearest Monday not correct for a Thursday", DayOfWeek.MONDAY, result.getDayOfWeek());
        assertEquals("Incorrect day of the month", 11, result.getDayOfMonth());
    }

    @Test
    public void testGetNearestMondayFromFriday() {
        LocalDate friday = LocalDate.of(2019, 2, 15);

        LocalDate result = AssignmentUtil.getNearestMonday(friday);

        assertEquals("Nearest Monday not correct for a Friday", DayOfWeek.MONDAY, result.getDayOfWeek());
        assertEquals("Incorrect day of the month", 18, result.getDayOfMonth());
    }

    @Test
    public void testGetNearestMondayFromSaturday() {
        LocalDate friday = LocalDate.of(2019, 2, 16);

        LocalDate result = AssignmentUtil.getNearestMonday(friday);

        assertEquals("Nearest Monday not correct for a Saturday", DayOfWeek.MONDAY, result.getDayOfWeek());
        assertEquals("Incorrect day of the month", 18, result.getDayOfMonth());
    }

}
