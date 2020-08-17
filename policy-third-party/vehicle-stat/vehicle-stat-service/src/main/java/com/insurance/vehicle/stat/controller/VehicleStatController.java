package com.insurance.vehicle.stat.controller;

import com.insurance.policy.admin.domain.VehicleInsured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class VehicleStatController {

    @RequestMapping("/vehicle/stat")
    public BigDecimal queryVehicleStat(@RequestBody  VehicleInsured vehicleInsured){
        long rand = System.currentTimeMillis();

        BigDecimal bigDecimal = new BigDecimal((rand % 10 + 0.1) / 10);

        return bigDecimal;
    }
}
