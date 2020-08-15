package com.insurance.policy.pay.service;

import com.insurance.policy.admin.domain.VehiclePolicyMain;
import org.springframework.stereotype.Service;

/**
 * @author jiangshuai
 * @date 2020/8/15 0015 10:30
 */
@Service
public interface VehiclePolicyService {
    VehiclePolicyMain selectVehiclePolicyById(long id);

    VehiclePolicyMain selectVehiclePolicyByPolicyNo(String policyNo);

    int updateVehiclePolicyStatues(long id);
}
