package com.sm.assignment.resource;


import com.sm.assignment.pojo.Fuel;
import com.sm.assignment.pojo.GetSavingsResponse;
import com.sm.assignment.service.SavingsService;
import com.sm.assignment.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
public class PriceInfoController {

    @Autowired
    private SavingsService savingsService;

    private static final String PETROL = "PETROL";
    private static final String DIESEL = "DIESEL";

    @RequestMapping(value = "/getSavings", method = RequestMethod.GET, produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    public GetSavingsResponse getSavings(@RequestParam("date") String historicDate,
                                         @RequestParam("mpg") Double mpg,
                                         @RequestParam("mileage") Double mileage,
                                         @RequestParam("fuel") String fuelType) {

        Logger.log("Request received to get historic prices");

        Logger.log("date is " + historicDate);
        Logger.log("fuel is "+ fuelType);
        Logger.log("mileage is "+ mileage);
        Logger.log("mpg is "+ mpg);

        Assert.notNull(historicDate, "Historic Date is required");
        Assert.notNull(mpg, "mpg is required");
        Assert.notNull(mileage, "mileage is required");
        Assert.notNull(fuelType, "fuel is required");

        Assert.isTrue(mpg > 0,"Invalid mpg supplied");
        Assert.isTrue(mileage > 0,"Invalid mileage supplied");
        Assert.isTrue(PETROL.equalsIgnoreCase(fuelType) || DIESEL.equalsIgnoreCase(fuelType),"Invalid fuel type supplied");
        GetSavingsResponse getSavingsResponse = new GetSavingsResponse();
        getSavingsResponse.setMessage(savingsService.getSavings(historicDate, mileage, mpg, Fuel.valueOf(fuelType.toUpperCase())));
        return  getSavingsResponse;
    }
}
