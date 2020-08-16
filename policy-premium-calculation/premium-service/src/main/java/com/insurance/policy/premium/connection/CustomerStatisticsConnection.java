package com.insurance.policy.premium.connection;

import com.insurance.customer.stat.api.CustomerStatApi;
import com.insurance.policy.admin.domain.VehicleCustomer;
import com.insurance.policy.premium.utils.SpringUtil;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

/**
 * 客客户指标系统线程连接
 */
public class CustomerStatisticsConnection implements Callable<BigDecimal> {

    private VehicleCustomer vehicleCustomer;

    private CustomerStatApi customerStatApi= (CustomerStatApi) SpringUtil.getBean(CustomerStatApi.class);

    public CustomerStatisticsConnection(VehicleCustomer vehicleCustomer) {
        this.vehicleCustomer = vehicleCustomer;

    }

    @Override
    public BigDecimal call(){
        try {
            return customerStatApi.queryCustomerStat(vehicleCustomer);
        }catch (Exception e){
            //如果调用客户指标系统报错，不影响整个流程，默认设置1作为客户指标系统的返回值
            e.printStackTrace();
            return new BigDecimal(1);
        }





    }
}
