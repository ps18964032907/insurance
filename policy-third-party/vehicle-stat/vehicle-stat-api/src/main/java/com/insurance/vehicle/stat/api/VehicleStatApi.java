package com.insurance.vehicle.stat.api;

import com.insurance.policy.admin.domain.VehicleInsured;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@FeignClient("vehicle-stat-service")
public interface VehicleStatApi {

    @RequestMapping("/vehicle/stat")
    public BigDecimal queryVehicleStat(@RequestBody VehicleInsured vehicleInsured);

}
