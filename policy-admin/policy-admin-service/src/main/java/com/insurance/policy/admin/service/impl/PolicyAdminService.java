package com.insurance.policy.admin.service.impl;

import com.insurance.policy.admin.domain.*;
import com.insurance.policy.admin.mapper.VehicleCustomerMapper;
import com.insurance.policy.admin.mapper.VehicleInsuredMapper;
import com.insurance.policy.admin.mapper.VehiclePolicyMainMapper;
//import com.insurance.policy.premium.feign.PolicyPremiumFeign;
import com.insurance.policy.premium.feign.PolicyPremiumFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/8/13 14:30
 */

@Service
public class PolicyAdminService {
    @Autowired
    PolicyPremiumFeign policyPremiumFeign;
    @Autowired
    VehiclePolicyMainMapper vehiclePolicyMainMapper;
    @Autowired
    VehicleCustomerMapper vehicleCustomerMapper;

    @Autowired
    VehicleInsuredMapper vehicleInsuredMapper;


    public void savePolicy(ComBinedPolicy comBinedPolicy) {

        ComBinedPolicy comBinedPolicy1 = policyPremiumFeign.calculatePolicy(comBinedPolicy);
        /*商业险*/
        CommercialPolicy commercialPolicy = comBinedPolicy.getCommercialPolicy();
        /*交强险*/
        CompulsoryPolicy compulsoryPolicy = comBinedPolicy.getCompulsoryPolicy();


        if (commercialPolicy != null) {
            /*商业险客户信息*/
            List<VehicleCustomer> commercialVehicleCustomerInfos = commercialPolicy.getVehicleCustomers();
            /*添加商业险客户信息*/
            vehicleCustomerMapper.insertVehicleCustomers(commercialVehicleCustomerInfos);
            /*保险主要信息*/
            VehiclePolicyMain commercialVehiclePolicyMainInfo = commercialPolicy.getVehiclePolicyMain();
            /*保存保险主要信息*/
            vehiclePolicyMainMapper.insertVehiclePolicyMain(commercialVehiclePolicyMainInfo);
            /*车辆信息*/
            VehicleInsured commercialVehicleInsured = commercialPolicy.getVehicleInsured();
            /*保存车辆信息*/
            vehicleInsuredMapper.insertVehicleInsured(commercialVehicleInsured);
        }
        if (compulsoryPolicy != null) {
            /*交强险客户信息*/
            List<VehicleCustomer> compulsoryVehicleCustomerInfos = compulsoryPolicy.getVehicleCustomers();
            /*添加交强险客户信息*/
            vehicleCustomerMapper.insertVehicleCustomers(compulsoryVehicleCustomerInfos);
            /*保险主要信息*/
            VehiclePolicyMain compulsoryVehiclePolicyMainInfo = compulsoryPolicy.getVehiclePolicyMain();
            /*保存保险主要信息*/
            vehiclePolicyMainMapper.insertVehiclePolicyMain(compulsoryVehiclePolicyMainInfo);
            /*车辆信息*/
            VehicleInsured compulsoryVehicleInsured = compulsoryPolicy.getVehicleInsured();
            /*保存车辆信息*/
            vehicleInsuredMapper.insertVehicleInsured(compulsoryVehicleInsured);
        }


    }
}
