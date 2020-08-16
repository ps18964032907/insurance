package com.insurance.policy.premium.connection;

import com.insurance.platform.api.InsurancePlatformApi;
import com.insurance.policy.admin.domain.CommercialPolicy;
import com.insurance.policy.admin.domain.VehiclePremCalSub;
import com.insurance.policy.premium.utils.SpringUtil;

import java.util.concurrent.Callable;

/**
 * 监管平台商业险线程连接
 */

public class IndustryPlatformConnectionForCMP implements Callable<VehiclePremCalSub> {
    private CommercialPolicy commercialPolicy;
    private InsurancePlatformApi insurancePlatformApi= (InsurancePlatformApi) SpringUtil.getBean("InsurancePlatformApi");



    public IndustryPlatformConnectionForCMP(CommercialPolicy commercialPolicy) {
        this.commercialPolicy = commercialPolicy;

    }

    public CommercialPolicy getCommercialPolicy() {
        return commercialPolicy;
    }


    @Override
    public VehiclePremCalSub call() throws Exception {
        return insurancePlatformApi.commercialQuery(commercialPolicy);
    }
}
