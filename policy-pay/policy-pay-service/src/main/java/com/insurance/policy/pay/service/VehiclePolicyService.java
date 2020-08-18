package com.insurance.policy.pay.service;

import com.insurance.policy.admin.domain.VehiclePolicyMain;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author jiangshuai
 * @date 2020/8/15 0015 10:30
 */
@Service
public interface VehiclePolicyService {

    VehiclePolicyMain selectVehiclePolicyByPolicyNo(String policyNo);

    int updateVehiclePolicyStatues(String policyNo);

    String payMoney(String policyNo, String name, String amount) throws Exception;

    String refund(String policyNo, BigDecimal amount) throws Exception;

    List<VehiclePolicyMain> policySelectByStatus();

    List<VehiclePolicyMain> refundSelect();
}
