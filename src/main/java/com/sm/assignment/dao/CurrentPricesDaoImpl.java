package com.sm.assignment.dao;

import com.sm.assignment.exception.ProcessingException;
import com.sm.assignment.pojo.Fuel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CurrentPricesDaoImpl implements CurrentPricesDao{

    //Logically today's prices should come from a dynamic source like database but as it
    //is not required to keep them up to date, keeping them here.
    public BigDecimal getTodaysPrice(Fuel fuel) {

        switch(fuel) {
            case PETROL:return new BigDecimal(128.00);
            case DIESEL:return new BigDecimal(125.50);
        }

        throw new ProcessingException("Invalid fuel type");
    }
}
