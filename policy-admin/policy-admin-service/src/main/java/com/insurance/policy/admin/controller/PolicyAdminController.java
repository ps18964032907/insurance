package com.insurance.policy.admin.controller;

import com.insurance.policy.admin.domain.ComBinedPolicy;
import com.insurance.policy.admin.domain.CommercialPolicy;
import com.insurance.policy.admin.service.impl.PolicyAdminService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/8/13 14:27
 */
@Controller
public class PolicyAdminController {

    @Autowired
    PolicyAdminService policyAdminService;


    /**
     * @return com.insurance.policy.admin.domain.CommercialPolicy 交强险和商业险组合对象
     * @Author 潘升
     * @Description //TODO 添加保单接口
     * @Date 12:19 2020/8/13
     * @Param [commercialPolicy] 交强险和商业险组合对象
     * @since 1.0.0
     */
    @RequestMapping("savePolicy")
    ComBinedPolicy savePolicy(@RequestBody ComBinedPolicy comBinedPolicy) {
        policyAdminService.savePolicy(comBinedPolicy);
        return null;
    }

    /**
     * @return void
     * @Author 潘升
     * @Description //TODO 核实单接口
     * @Date 12:25 2020/8/13
     * @Param [commercialPolicy]
     * @since 1.0.0
     */
    @RequestMapping("checkPolicy")
    void checkPolicy(@RequestBody CommercialPolicy commercialPolicy) {

    }

    /**
     * @return void
     * @Author 潘升
     * @Description //TODO 缴费接口
     * @Date 14:03 2020/8/13
     * @Param []
     * @since 1.0.0
     */
    @RequestMapping("payPolicy")
    @ResponseBody
    String payPolicy() {
        System.out.println("aaaaaaaaaa");
        return "aaaaa";
    }


}
