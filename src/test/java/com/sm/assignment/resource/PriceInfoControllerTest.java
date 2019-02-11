package com.sm.assignment.resource;

import com.sm.assignment.pojo.Fuel;
import com.sm.assignment.pojo.GetSavingsResponse;
import com.sm.assignment.service.SavingsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PriceInfoControllerTest {

    @Mock
    private SavingsService savingsService;

    @InjectMocks
    private PriceInfoController priceInfoController;

    @Test
    public void testGetSavings() {
        String historicDate = "12/12/2012";
        Double mileage = 100d;
        Double mpg = 10d;
        Fuel petrol = Fuel.PETROL;

        String result = "wow!! its so cheap";
        GetSavingsResponse getSavingsResponse = new GetSavingsResponse();
        getSavingsResponse.setMessage(result);
        when(savingsService.getSavings(eq(historicDate), any(Double.class), any(Double.class), eq(petrol))).thenReturn(result);

        Assert.assertEquals("Not returned correct message", result, priceInfoController.getSavings(historicDate, mileage, mpg, "petrol").getMessage());

        verify(savingsService, times(1)).getSavings(eq(historicDate), any(Double.class), any(Double.class), eq(petrol));

    }
}
