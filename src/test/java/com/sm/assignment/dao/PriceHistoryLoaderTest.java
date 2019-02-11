package com.sm.assignment.dao;

import com.sm.assignment.pojo.HistoricalPrice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class PriceHistoryLoaderTest {

    @Mock
    private HistoricalPricesDao historicalPricesDao;

    @InjectMocks
    private PriceHistoryLoader priceHistoryLoader;

    @Test
    public void testLoadPrices() {
        priceHistoryLoader.loadPrices("fuelprices.csv");

        HistoricalPrice historicalPrice = new HistoricalPrice();
        historicalPrice.setUnleadedDieselPrice(new BigDecimal("76.77"));
        historicalPrice.setUnleadedPetrolPrice(new BigDecimal("74.59"));

        Assert.assertEquals("Prices not loaded", true, priceHistoryLoader.isPriceHistoryAvailable());
        verify(historicalPricesDao, times(3)).putHistoricalPrice(any(String.class), any(HistoricalPrice.class));

        verify(historicalPricesDao, times(1)).putHistoricalPrice(eq("07/06/2004"), refEq(historicalPrice));

        historicalPrice.setUnleadedDieselPrice(new BigDecimal("76.69"));
        historicalPrice.setUnleadedPetrolPrice(new BigDecimal("74.47"));
        verify(historicalPricesDao, times(1)).putHistoricalPrice(eq("14/06/2004"), refEq(historicalPrice));
        historicalPrice.setUnleadedDieselPrice(new BigDecimal("76.62"));
        historicalPrice.setUnleadedPetrolPrice(new BigDecimal("74.42"));
        verify(historicalPricesDao, times(1)).putHistoricalPrice(eq("21/06/2004"), refEq(historicalPrice));
    }
}
