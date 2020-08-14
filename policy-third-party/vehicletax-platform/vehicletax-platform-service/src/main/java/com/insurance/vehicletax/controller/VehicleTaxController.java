package com.insurance.vehicletax.controller;


import com.insurance.policy.admin.domain.VehicleInsured;
import com.insurance.policy.admin.domain.VehicleTax;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/vehicletax/platform")
public class VehicleTaxController {

    @RequestMapping
    public VehicleTax queryVehicleTax(@RequestBody VehicleInsured vehicleInsured){
        VehicleTax vehicleTax = new VehicleTax();
        vehicleTax.setPreviousTax(new BigDecimal(400));
        vehicleTax.setStdTax(new BigDecimal(450));
        vehicleTax.setTaxPayerName("张三");
        vehicleTax.setTaxPayerNo("123423221");
        vehicleTax.setTel("13900001222");
        vehicleTax.setTotalTaxAmount(new BigDecimal(100));
        vehicleTax.setAddress("浦东软件园");
        vehicleTax.setCurrentTax(new BigDecimal(500));
        vehicleTax.setOverdueFee(new BigDecimal(100));

        return vehicleTax;
    }
}
