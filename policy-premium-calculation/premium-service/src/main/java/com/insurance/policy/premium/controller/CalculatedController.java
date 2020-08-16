package com.insurance.policy.premium.controller;

import com.insurance.policy.admin.domain.ComBinedPolicy;
import com.insurance.policy.premium.service.CalculatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

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
    @Autowired
    CalculatedService calculatedService;
    @RequestMapping("/calculatePolicy")
    public ComBinedPolicy calculatePolicy(@RequestBody ComBinedPolicy comBinedPolicy){
        try {
            return calculatedService.calculatedPremium(comBinedPolicy);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

