package com.insurance.policy.premium.connection;

import com.insurance.policy.admin.domain.VehicleInsured;
import com.insurance.policy.premium.utils.SpringUtil;
import com.insurance.vehicle.stat.api.VehicleStatApi;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

/**
 * 车辆指标系统VehicleStatApi线程连接
 */
public class VehicleStatisticsConnection implements Callable<BigDecimal> {
    private VehicleInsured vehicleInsured;
    private VehicleStatApi vehicleStatApi= (VehicleStatApi) SpringUtil.getBean(VehicleStatApi.class);

    public VehicleStatisticsConnection(VehicleInsured vehicleInsured) {
        this.vehicleInsured = vehicleInsured;

    }

    @Override
    public BigDecimal call(){
        try {
            return vehicleStatApi.queryVehicleStat(vehicleInsured);
        }catch (Exception e){
            //如果调用车辆指标系统报错，不影响整个流程，默认设置1作为车辆指标系统的返回值
            e.printStackTrace();
            return new BigDecimal(1);
        }
    }
}
