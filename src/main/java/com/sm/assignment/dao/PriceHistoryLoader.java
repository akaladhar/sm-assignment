package com.sm.assignment.dao;

import com.sm.assignment.exception.ProcessingException;
import com.sm.assignment.pojo.HistoricalPrice;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class PriceHistoryLoader implements InitializingBean {

    private static final String SEPARATOR = ",";

    @Autowired
    private HistoricalPricesDao historicalPricesDao;

    private boolean isPriceHistoryAvailable = false;

    public void loadPrices(String priceHistoryFile) {

        try {
            Path path = Paths.get(getClass().getClassLoader().getResource(priceHistoryFile).toURI());

            Stream<String> lines = Files.lines(path);
            lines.skip(3).forEach(this::setHistoricalPrice);
            lines.close();
            isPriceHistoryAvailable = true;
        }
        catch(URISyntaxException urise) {
            throw new ProcessingException(urise.getMessage());
        }
        catch(IOException ioe) {
            throw new ProcessingException(ioe.getMessage());
        }
    }

    public boolean isPriceHistoryAvailable() {
        return isPriceHistoryAvailable;
    }

    private void setHistoricalPrice(String line) {
        HistoricalPrice historicalPrice = new HistoricalPrice();
        String[] values = line.split(SEPARATOR);
        historicalPrice.setUnleadedPetrolPrice(new BigDecimal(values[1]));
        historicalPrice.setUnleadedDieselPrice(new BigDecimal(values[2]));
        historicalPricesDao.putHistoricalPrice(values[0], historicalPrice);
    }

    public void afterPropertiesSet() {
       loadPrices("fuelprices.csv");
    }


}
