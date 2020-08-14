package com.insurance.policy.admin.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.insurance.policy.admin.domain.*;
import com.insurance.policy.admin.mapper.*;
import com.insurance.policy.admin.service.PolicyService;
import com.insurance.policy.premium.feign.PolicyPremiumFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {
    @Autowired
    PolicyPremiumFeign policyPremiumFeign;

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

    @Autowired
    private VehiclePremCalSubMapper vehiclePremCalSubMapper;


    private static Snowflake snowflake = IdUtil.getSnowflake(1, 1);


    public static void main(String[] args) {
        System.out.println("CCL" + snowflake.nextIdStr());
        System.out.println("CPL" + snowflake.nextIdStr());
    }

    /**
     * 根据保单的id查询出两个保单的所有信息
     *
     * @param id
     * @return
     */
    @Override
    public ComBinedPolicy getCombinedPolicy(Long id) {


        //创建保单对象
        //交强险
        CompulsoryPolicy compulsoryPolicy = new CompulsoryPolicy();

        //商业险
        CommercialPolicy commercialPolicy = new CommercialPolicy();


        String flag = "1";//标志这个id是交强险的还是商业险的，1代表交强险，0代表商业险


        //1、查询保单主表的信息
        VehiclePolicyMain vehiclePolicyMain = vehiclePolicyMainMapper.selectVehiclePolicyMainById(id);
        //获取关联的policy的id
        Long associatedPolicyId = vehiclePolicyMain.getAssociatedPolicyId();

        //1.1查询关联保单信息
        VehiclePolicyMain associatedVehiclePolicyMain = vehiclePolicyMainMapper.selectVehiclePolicyMainById(associatedPolicyId);


        //如果是交强险
        if ("1".equals(vehiclePolicyMain.getCompulsory())) {
            flag = "1";
        } else {
            flag = "0";
        }


        //2、查询责任信息
        //查询保单对应的责任信息
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
//        VehicleInsured associatedVehicleInsured = new VehicleInsured();
//        associatedVehicleInsured.setPolicyId(associatedPolicyId);
//        List<VehicleInsured> vehicleInsureds2 = vehicleInsuredMapper.selectVehicleInsuredList(associatedVehicleInsured);
//        VehicleInsured vehicleInsured2 = vehicleInsureds.get(0);


        //4查询客户信息
        VehicleCustomer vehicleCustomer = new VehicleCustomer();
        vehicleCoverage.setPolicyId(id);
        List<VehicleCustomer> vehicleCustomers = vehicleCustomerMapper.selectVehicleCustomerList(vehicleCustomer);

        //4.1
//        VehicleCustomer associatedVehicleCustomer = new VehicleCustomer();
//        associatedVehicleCustomer.setPolicyId(associatedPolicyId);
//        List<VehicleCustomer> associatedVechicleCustomers = vehicleCustomerMapper.selectVehicleCustomerList(associatedVehicleCustomer);


        //5.查询保费计算辅助表信息
        VehiclePremCalSub vehiclePremCalSub = new VehiclePremCalSub();
        vehiclePremCalSub.setPolicyId(id);
        List<VehiclePremCalSub> vehiclePremCalSubs = vehiclePremCalSubMapper.selectVehiclePremCalSubList(vehiclePremCalSub);
        VehiclePremCalSub vehiclePremCalSub1 = vehiclePremCalSubs.get(0);

        VehiclePremCalSub associatedVehiclePremSub = new VehiclePremCalSub();
        associatedVehiclePremSub.setPolicyId(associatedPolicyId);
        List<VehiclePremCalSub> associatedVehiclePremSubs1 = vehiclePremCalSubMapper.selectVehiclePremCalSubList(associatedVehiclePremSub);
        VehiclePremCalSub associatedVehiclePremCalSub = associatedVehiclePremSubs1.get(0);

        //6.查询车船税
        VehicleTax vehicleTax = new VehicleTax();

        //如果是交强险
        if ("1".equals(flag)) {
            vehicleTax.setPolicyId(id);
        } else {
            vehicleTax.setPolicyId(associatedPolicyId);
        }

        List<VehicleTax> vehicleTaxes = vehicleTaxMapper.selectVehicleTaxList(vehicleTax);
        VehicleTax vehicleTax1 = vehicleTaxes.get(0);


        if ("1".equals(flag)) {
            //交强险
            compulsoryPolicy.setVehicleCoverages(vehicleCoverages);
            compulsoryPolicy.setVehicleCustomers(vehicleCustomers);
            compulsoryPolicy.setVehicleInsured(vehicleInsured1);
            compulsoryPolicy.setVehiclePolicyMain(vehiclePolicyMain);
            compulsoryPolicy.setVehiclePremCalSub(vehiclePremCalSub1);
            compulsoryPolicy.setVehicleTax(vehicleTax1);

            //商业险
            commercialPolicy.setVehicleCoverages(associatedVehicleCoverages);
            commercialPolicy.setVehicleCustomers(vehicleCustomers);
            commercialPolicy.setVehicleInsured(vehicleInsured1);
            commercialPolicy.setVehiclePolicyMain(associatedVehiclePolicyMain);
            commercialPolicy.setVehiclePremCalSub(associatedVehiclePremCalSub);

        } else {
            //商业险
            commercialPolicy.setVehicleCoverages(vehicleCoverages);
            commercialPolicy.setVehicleCustomers(vehicleCustomers);
            commercialPolicy.setVehicleInsured(vehicleInsured1);
            commercialPolicy.setVehiclePolicyMain(vehiclePolicyMain);
            commercialPolicy.setVehiclePremCalSub(vehiclePremCalSub1);

            //交强险
            compulsoryPolicy.setVehicleCoverages(associatedVehicleCoverages);
            compulsoryPolicy.setVehicleCustomers(vehicleCustomers);
            compulsoryPolicy.setVehicleInsured(vehicleInsured1);
            compulsoryPolicy.setVehiclePolicyMain(associatedVehiclePolicyMain);
            compulsoryPolicy.setVehiclePremCalSub(associatedVehiclePremCalSub);
            compulsoryPolicy.setVehicleTax(vehicleTax1);

        }

        ComBinedPolicy comBinedPolicy = new ComBinedPolicy();
        comBinedPolicy.setCommercialPolicy(commercialPolicy);
        comBinedPolicy.setCompulsoryPolicy(compulsoryPolicy);

        return comBinedPolicy;
    }

    /**
     * 查询保单列表
     *
     * @return
     */
    @Override
    public List<ComBinedPolicy> selectVehiclePolicyList() {
        List<ComBinedPolicy> comBinedPolicies = new ArrayList<>();

        List<VehiclePolicyMain> vehiclePolicyMains = vehiclePolicyMainMapper.selectVehiclePolicyMainList(new VehiclePolicyMain());
        for (VehiclePolicyMain vehiclePolicyMain : vehiclePolicyMains) {
            comBinedPolicies.add(getCombinedPolicy(vehiclePolicyMain.getId()));
        }

        return comBinedPolicies;
    }

    /**
     * 保存保单数据
     *
     * @param combinedPolicy
     * @return
     */
    @Override
    public int create(ComBinedPolicy combinedPolicy) {

        /*校验*/

//        ComBinedPolicy comBinedPolicy1 = policyPremiumFeign.calculatePolicy(combinedPolicy);
        /*商业险*/
        CommercialPolicy commercialPolicy = combinedPolicy.getCommercialPolicy();
        /*交强险*/
        CompulsoryPolicy compulsoryPolicy = combinedPolicy.getCompulsoryPolicy();


        /*保险主要信息*/
        VehiclePolicyMain commercialVehiclePolicyMainInfo = commercialPolicy.getVehiclePolicyMain();
        //设置为已报价
        commercialVehiclePolicyMainInfo.setPolicyStatus("2");
        commercialVehiclePolicyMainInfo.setPolicyNo("CCL" + snowflake.nextIdStr());

        /*保存保险主要信息*/
        vehiclePolicyMainMapper.insertVehiclePolicyMain(commercialVehiclePolicyMainInfo);
        /*商业险客户信息*/
        List<VehicleCustomer> commercialVehicleCustomerInfos = commercialPolicy.getVehicleCustomers();
        commercialVehicleCustomerInfos.forEach(vehicleCustomer -> vehicleCustomer.setPolicyId(commercialVehiclePolicyMainInfo.getId()));

        /*添加商业险客户信息*/
        vehicleCustomerMapper.insertVehicleCustomers(commercialVehicleCustomerInfos);
        /*车辆信息*/
        VehicleInsured commercialVehicleInsured = commercialPolicy.getVehicleInsured();
        commercialVehicleInsured.setPolicyId(commercialVehiclePolicyMainInfo.getId());
        /*保存车辆信息*/
        vehicleInsuredMapper.insertVehicleInsured(commercialVehicleInsured);

//        vehicleCoverageMapper.insertVehicleCoverage()




        /*保险主要信息*/
        VehiclePolicyMain compulsoryVehiclePolicyMainInfo = compulsoryPolicy.getVehiclePolicyMain();
        //设置为已报价
        compulsoryVehiclePolicyMainInfo.setPolicyStatus("2");
        //设置来源
        compulsoryVehiclePolicyMainInfo.setBusinessSourceCode(commercialVehiclePolicyMainInfo.getBusinessSourceCode());
        //设置编号
        compulsoryVehiclePolicyMainInfo.setPolicyNo("CPL" + snowflake.nextIdStr());

        /*保存保险主要信息*/
        vehiclePolicyMainMapper.insertVehiclePolicyMain(compulsoryVehiclePolicyMainInfo);
        /*交强险客户信息*/
        List<VehicleCustomer> compulsoryVehicleCustomerInfos = compulsoryPolicy.getVehicleCustomers();
        compulsoryVehicleCustomerInfos.forEach(vehicleCustomer -> vehicleCustomer.setPolicyId(compulsoryVehiclePolicyMainInfo.getId()));

        /*添加交强险客户信息*/
        vehicleCustomerMapper.insertVehicleCustomers(compulsoryVehicleCustomerInfos);
        /*车辆信息*/
        VehicleInsured compulsoryVehicleInsured = compulsoryPolicy.getVehicleInsured();
        compulsoryVehicleInsured.setPolicyId(compulsoryVehiclePolicyMainInfo.getId());

        /*保存车辆信息*/
        vehicleInsuredMapper.insertVehicleInsured(compulsoryVehicleInsured);


        return 1;
    }

    /**
     * 核保
     *
     * @param id
     * @return
     */
    @Override
    public int underwriting(Long id) {


        //这里只是传入了一个id,根据这个id,从t_vehicle_policy_main表找到policy对象，找到关联的另外一个policy对象以后，在进行以后的步骤
        return 0;
    }

    /**
     * 根据一个保单id给两个保单收费
     *
     * @param id
     * @return
     */
    @Override
    public int collect(Long id) {
        //这里只是传入了一个id,根据这个id,从t_vehicle_policy_main表找到policy对象，找到关联的另外一个policy对象以后，在进行以后的步骤
        return 0;
    }

    /**
     * 查询待核保的保单列表
     *
     * @return
     */
    @Override
    public List<VehiclePolicyMain> queryUnderwriting() {


        return vehiclePolicyMainMapper.queryUnderwriting();
    }

    /**
     * 查询待收费的保单列表
     *
     * @return
     */
    @Override
    public List<VehiclePolicyMain> queryCollect() {
        return null;
    }
}
