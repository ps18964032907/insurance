package com.insurance.policy.admin.service;

import com.insurance.policy.admin.domain.ComBinedPolicy;

public interface PolicyService {

    ComBinedPolicy getCombinedPolicy(Long policyId);
}
