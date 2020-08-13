package com.insurance.policy.admin.service.impl;

import com.insurance.policy.admin.domain.ComBinedPolicy;
import com.insurance.policy.admin.domain.VehicleCoverage;
import com.insurance.policy.admin.domain.VehicleInsured;
import com.insurance.policy.admin.domain.VehiclePolicyMain;
import com.insurance.policy.admin.mapper.*;
import com.insurance.policy.admin.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private VehicleCoverageMapper vehicleCoverageMapper;

    @Autowired
    private VehicleCustomerMapper vehicleCustomerMapper;

    @Autowired
    private VehicleInsuredMapper vehicleInsuredMapper;

    @Autowired
    private VehiclePolicyMainMapper vehiclePolicyMainMapper;

    @Autowired
    private VehicleTaxMapper vehicleTaxMapper;


    /**
     * 根据保单的id查询出两个保单的所有信息
     * @param id
     * @return
     */
    @Override
    public ComBinedPolicy getCombinedPolicy(Long id) {

        //1、查询保单主表的信息
        VehiclePolicyMain vehiclePolicyMain = vehiclePolicyMainMapper.selectVehiclePolicyMainById(id);
        //获取关联的policy的id
        Long associatedPolicyId = vehiclePolicyMain.getAssociatedPolicyId();


        //2、查询客户信息
        //查询保单对应的客户信息
        VehicleCoverage vehicleCoverage = new VehicleCoverage();
        vehicleCoverage.setPolicyId(id);
        List<VehicleCoverage> vehicleCoverages = vehicleCoverageMapper.selectVehicleCoverageList(vehicleCoverage);
        //2.1查询关联保单对应的客户信息
        VehicleCoverage associatedCoverayge = new VehicleCoverage();
        associatedCoverayge.setPolicyId(associatedPolicyId);
        List<VehicleCoverage> associatedVehicleCoverages = vehicleCoverageMapper.selectVehicleCoverageList(associatedCoverayge);

        //3、查询车辆信息
        VehicleInsured vehicleInsured = new VehicleInsured();
        vehicleInsured.setPolicyId(id);
        List<VehicleInsured> vehicleInsureds = vehicleInsuredMapper.selectVehicleInsuredList(vehicleInsured);
        VehicleInsured vehicleInsured1 = vehicleInsureds.get(0);

        //3.1查询关联的保单的车辆信息
        VehicleInsured associatedVehicleInsured = new VehicleInsured();
        associatedVehicleInsured.setPolicyId(associatedPolicyId);
        List<VehicleInsured> vehicleInsureds2 = vehicleInsuredMapper.selectVehicleInsuredList(associatedVehicleInsured);
        VehicleInsured vehicleInsured2 = vehicleInsureds.get(0);


        //4查询险别信息

        return null;
    }
}
