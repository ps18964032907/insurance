package com.insurance.customer.stat.api;

import com.insurance.policy.admin.domain.VehicleCustomer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@FeignClient("customer-stat-service")
public interface CustomerStatApi {
    @RequestMapping("/customer/stat")
    public BigDecimal queryCustomerStat(@RequestBody VehicleCustomer vehicleCustomer);
}
