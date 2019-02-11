package com.sm.assignment.pojo;

import com.sm.assignment.exception.ProcessingException;

import java.math.BigDecimal;
import java.util.Objects;

public class HistoricalPrice {
    private BigDecimal unleadedPetrolPrice;

    private BigDecimal unleadedDieselPrice;

    public BigDecimal getUnleadedPetrolPrice() {
        return unleadedPetrolPrice;
    }

    public void setUnleadedPetrolPrice(BigDecimal unleadedPetrolPrice) {
        this.unleadedPetrolPrice = unleadedPetrolPrice;
    }

    public BigDecimal getUnleadedDieselPrice() {
        return unleadedDieselPrice;
    }

    public void setUnleadedDieselPrice(BigDecimal unleadedDieselPrice) {
        this.unleadedDieselPrice = unleadedDieselPrice;
    }

    public BigDecimal getPrice(Fuel fuel) {
        switch (fuel) {
            case PETROL: return unleadedPetrolPrice;
            case DIESEL: return unleadedDieselPrice;
        }

        throw new ProcessingException("Invalid Fuel type");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricalPrice that = (HistoricalPrice) o;
        return Objects.equals(unleadedPetrolPrice, that.unleadedPetrolPrice) &&
                Objects.equals(unleadedDieselPrice, that.unleadedDieselPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unleadedPetrolPrice, unleadedDieselPrice);
    }
}
