package com.sm.assignment.service;

import com.sm.assignment.pojo.Fuel;

public interface SavingsService {
    String getSavings(String date, Double milleage, Double mpg, Fuel fuelType);
}
