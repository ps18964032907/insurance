package com.insurance.vehicletax.api;

import com.insurance.policy.admin.domain.VehicleInsured;
import com.insurance.policy.admin.domain.VehicleTax;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/vehicletax/platform")
@FeignClient("vehicletax-platform-service")
public interface VehicleTaxApi {
    @RequestMapping
    public VehicleTax queryVehicleTax(@RequestBody VehicleInsured vehicleInsured);
}
