package com.insurance.policy.premium.controller;

import com.insurance.policy.admin.domain.ComBinedPolicy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mhd
 * @className CalculatedController
 * @description TODO
 * @create 2020/8/13
 * @since 1.0.0
 */
@RestController
@RequestMapping("/premium")
public class CalculatedController {
    @RequestMapping("/calculatePolicy")
    public ComBinedPolicy calculatePolicy(ComBinedPolicy comBinedPolicy){
            return null;
    }
}
