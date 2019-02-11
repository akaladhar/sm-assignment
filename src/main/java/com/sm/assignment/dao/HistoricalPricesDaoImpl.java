package com.sm.assignment.dao;

import com.sm.assignment.pojo.HistoricalPrice;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HistoricalPricesDaoImpl implements HistoricalPricesDao {

    private static Map<String, HistoricalPrice> historicalPriceMap = new HashMap<>();

    public HistoricalPricesDaoImpl() {
    }

    public HistoricalPrice getHistoricPrice(String date) {
        return historicalPriceMap.get(date);
    }

    public  void putHistoricalPrice(String date, HistoricalPrice historicalPrice) {
        historicalPriceMap.put(date, historicalPrice);
    }
}
