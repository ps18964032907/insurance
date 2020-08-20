package com.insurance.policy.pay.feign;

import com.insurance.policy.admin.domain.ComBinedPolicy;
import com.insurance.policy.admin.domain.VehiclePolicyMain;
import com.insurance.policy.pay.domain.VehicleCollection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author jiangshuai
 * @date 2020/8/13 0013 12:04
 */
@FeignClient(value = "policy-pay-service",path = "/pay")
public interface PolicyPayFeign {

    /**
     * 核单
     *
     * @param vehicleCollection 保单收费对象
     * @return 险种责任信息
     */

    @RequestMapping("underwriting")
    public int underwriting(VehicleCollection vehicleCollection);

    /**
     * 缴费
     *
     * @param comBinedPolicy 交强险和商业险组合对象
     * @return 险种责任信息
     */
    @RequestMapping("collect")
    public String collect(ComBinedPolicy comBinedPolicy);
}
