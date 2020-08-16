package com.insurance.policy.premium.connection;

import com.insurance.platform.api.InsurancePlatformApi;
import com.insurance.policy.admin.domain.CompulsoryPolicy;
import com.insurance.policy.admin.domain.VehiclePremCalSub;
import com.insurance.policy.premium.utils.SpringUtil;

import java.util.concurrent.Callable;

/**
 * 监管平台交强险线程连接
 */

public class IndustryPlatformConnectionForCPP implements Callable<VehiclePremCalSub> {
    private CompulsoryPolicy compulsoryPolicy;
    private InsurancePlatformApi insurancePlatformApi= (InsurancePlatformApi) SpringUtil.getBean(InsurancePlatformApi.class);


    public IndustryPlatformConnectionForCPP(CompulsoryPolicy compulsoryPolicy) {
        this.compulsoryPolicy = compulsoryPolicy;

    }

    public CompulsoryPolicy getCommercialPolicy() {
        return compulsoryPolicy;
    }


    @Override
    public VehiclePremCalSub call() throws Exception {
        return insurancePlatformApi.compulsoryQuery(compulsoryPolicy);
    }
}
