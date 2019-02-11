package com.sm.assignment.dao;

import com.sm.assignment.pojo.Fuel;

import java.math.BigDecimal;

public interface CurrentPricesDao {
    BigDecimal getTodaysPrice(Fuel fuel);
}
