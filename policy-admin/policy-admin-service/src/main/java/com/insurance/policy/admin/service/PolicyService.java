package com.insurance.policy.admin.service;

import com.insurance.policy.admin.domain.ComBinedPolicy;
import com.insurance.policy.admin.domain.VehiclePolicyMain;

import java.util.List;

public interface PolicyService {

    ComBinedPolicy getCombinedPolicy(Long policyId);

    List<ComBinedPolicy> selectVehiclePolicyList();

    int create(ComBinedPolicy combinedPolicy);

    int underwriting(Long id);

    int collect(Long id);

    List<VehiclePolicyMain> queryUnderwriting();

    List<VehiclePolicyMain> queryCollect();

}
