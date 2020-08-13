package com.insurance.policy.admin.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("combined")
@RestController
public class PolicyController {


    @GetMapping("policy")
    public AjaxResult getCombinedPolicy(Long policyId){
        return null;
    }
}
