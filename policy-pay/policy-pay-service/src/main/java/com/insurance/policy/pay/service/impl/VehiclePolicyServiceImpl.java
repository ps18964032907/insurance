package com.insurance.policy.pay.service.impl;

import com.insurance.policy.admin.domain.VehiclePolicyMain;
import com.insurance.policy.pay.mapper.VehiclePayMapper;
import com.insurance.policy.pay.service.VehiclePolicyService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jiangshuai
 * @date 2020/8/15 0015 10:32
 */
public class VehiclePolicyServiceImpl implements VehiclePolicyService {
    @Autowired
    VehiclePayMapper vehiclePayMapper;
    @Override
    public VehiclePolicyMain selectVehiclePolicyById(long id) {
        return vehiclePayMapper.selectVehiclePolicyById(id);
    }

    @Override
    public VehiclePolicyMain selectVehiclePolicyByPolicyNo(String policyNo) {
        return vehiclePayMapper.selectVehiclePolicyByPolicyNo(policyNo);
    }

    @Override
    public int updateVehiclePolicyStatues(long id) {
        return vehiclePayMapper.updateVehiclePolicyStatues(id);
    }
}
