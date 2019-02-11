package com.sm.assignment.dao;

import com.sm.assignment.pojo.HistoricalPrice;

public interface HistoricalPricesDao {
    HistoricalPrice getHistoricPrice(String date);

    void putHistoricalPrice(String date, HistoricalPrice historicalPrice);
}
