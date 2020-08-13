package com.insurance.policy.admin.feign;

import com.insurance.policy.admin.domain.CommercialPolicy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/8/13 11:48
 */
@FeignClient("policy-admin-service")
public interface PolicyAdminFeign {

    /**
     * @return com.insurance.policy.admin.domain.CommercialPolicy 交强险和商业险组合对象
     * @Author 潘升
     * @Description //TODO 添加保单接口
     * @Date 12:19 2020/8/13
     * @Param [commercialPolicy] 交强险和商业险组合对象
     * @since 1.0.0
     */
    @RequestMapping("savePolicy")
    CommercialPolicy savePolicy(CommercialPolicy commercialPolicy);

    /**
     * @return void
     * @Author 潘升
     * @Description //TODO 核实单接口
     * @Date 12:25 2020/8/13
     * @Param [commercialPolicy]
     * @since 1.0.0
     */
    @RequestMapping("checkPolicy")
    void checkPolicy(CommercialPolicy commercialPolicy);
    
    /**
     * @Author 潘升 
     * @Description //TODO 缴费接口
     * @Date 14:03 2020/8/13
     * @Param [] 
     * @since 1.0.0
     * @return void
    */
    @RequestMapping("payPolicy")
    void payPolicy();

/*
    @RequestMapping("producePolicy")
    void producePolicy();
*/


}
