package com.insurance.policy.admin.domain;


import lombok.Data;

import javax.validation.Valid;
import java.util.List;

/**
 * 交强险对象
 */
@Data
public class CompulsoryPolicy {
    //保单主要信息
    @Valid
    private VehiclePolicyMain vehiclePolicyMain;
    //险别信息
    @Valid
    private List<VehicleCoverage> vehicleCoverages;
    //保费计算辅助信息
    @Valid
    private VehiclePremCalSub vehiclePremCalSub;
    //车船税信息
    @Valid
    private VehicleTax vehicleTax;
    //客户信息
    @Valid
    private List<VehicleCustomer> vehicleCustomers;
    //被保险车辆信息
    @Valid
    private VehicleInsured vehicleInsured;
}
