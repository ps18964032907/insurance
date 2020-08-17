package com.insurance.customer.stat.controller;

import com.insurance.policy.admin.domain.VehicleCustomer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CustomerStatController {

    @RequestMapping("/customer/stat")
    public BigDecimal queryCustomerStat(@RequestBody VehicleCustomer vehicleCustomer){
        long rand = System.currentTimeMillis();

        BigDecimal bigDecimal = new BigDecimal((rand % 10 + 0.1) / 10);

        return bigDecimal;
    }
}
