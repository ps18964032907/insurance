package com.insurance.policy.pay.mapper;

import com.insurance.policy.admin.domain.VehiclePolicyMain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author jiangshuai
 * @date 2020/8/15 0015 10:31
 */
@Mapper
@Component
public interface VehiclePayMapper {

    VehiclePolicyMain selectVehiclePolicyById(@Param("id") long id);

    VehiclePolicyMain selectVehiclePolicyByPolicyNo(@Param("policyNo") String policyNo);

    int updateVehiclePolicyStatues(@Param("id") long id);
}
