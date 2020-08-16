package com.insurance.policy.premium.connection;

import com.insurance.policy.admin.domain.VehicleInsured;
import com.insurance.policy.admin.domain.VehicleTax;
import com.insurance.policy.premium.utils.SpringUtil;
import com.insurance.vehicletax.api.VehicleTaxApi;

import java.util.concurrent.Callable;

/**
 * 车船税平台线程连接
 */
public class VehicleTaxPlatformConnection implements Callable<VehicleTax> {
    private VehicleInsured vehicleInsured;
    private VehicleTaxApi vehicleTaxApi = (VehicleTaxApi) SpringUtil.getBean(VehicleTaxApi.class);

    public VehicleTaxPlatformConnection(VehicleInsured vehicleInsured) {
        this.vehicleInsured = vehicleInsured;

    }

    @Override
    public VehicleTax call() throws Exception {
        return vehicleTaxApi.queryVehicleTax(vehicleInsured);
    }
}
