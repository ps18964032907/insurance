package com.insurance.policy.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/8/13 11:48
 */
@FeignClient("policy-admin-service")
public interface PolicyAdminFeign {

}
