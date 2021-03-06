package com.insurance.policy.admin.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.insurance.platform.api.InsurancePlatformApi;
import com.insurance.policy.admin.domain.*;
import com.insurance.policy.admin.mapper.*;
import com.insurance.policy.admin.service.PolicyService;
//import com.insurance.policy.message.fegin.PolicyMessageCenterFegin;
import com.insurance.policy.message.fegin.PolicyMessageCenterFegin;
import com.insurance.policy.pay.domain.VehicleCollection;
import com.insurance.policy.pay.feign.PolicyPayFeign;
import com.insurance.policy.premium.feign.PolicyPremiumFeign;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
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

    @Autowired
    private PolicyPayFeign policyPayFeign;

    @Autowired
    private InsurancePlatformApi insurancePlatformApi;

    private static Snowflake snowflake = IdUtil.getSnowflake(1, 1);

    //    private PolicyMessageCenterFegin policyMessageCenterFegin;
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
     * @param combinedPolicy1
     * @return
     */
    @Override
    public int create(ComBinedPolicy combinedPolicy1) {


        ComBinedPolicy combinedPolicy = policyPremiumFeign.calculatePolicy(combinedPolicy1);

        System.out.println(combinedPolicy);

        /*商业险*/
        CommercialPolicy commercialPolicy = combinedPolicy.getCommercialPolicy();
        /*交强险*/
        CompulsoryPolicy compulsoryPolicy = combinedPolicy.getCompulsoryPolicy();

/*        //险别信息
//    @Valid
        private List<VehicleCoverage> vehicleCoverages;

        //保费计算辅助信息
//    @Valid
        private VehiclePremCalSub vehiclePremCalSub;*/



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


        List<VehicleCoverage> vehicleCoverages = commercialPolicy.getVehicleCoverages();

        //添加险种责任信息对象
        for (VehicleCoverage vehicleCoverage : vehicleCoverages) {
            vehicleCoverage.setPolicyId(commercialVehiclePolicyMainInfo.getId());
            vehicleCoverageMapper.insertVehicleCoverage(vehicleCoverage);
        }
        VehiclePremCalSub vehiclePremCalSub = commercialPolicy.getVehiclePremCalSub();
        vehiclePremCalSub.setPolicyId(commercialVehiclePolicyMainInfo.getId());
        //保费计算辅助信息

        vehiclePremCalSubMapper.insertVehiclePremCalSub(vehiclePremCalSub);

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

        List<VehicleCoverage> vehicleCoverages1 = compulsoryPolicy.getVehicleCoverages();

        //设置交强保险的关联保单
        compulsoryVehiclePolicyMainInfo.setAssociatedPolicyId(commercialVehiclePolicyMainInfo.getId());
        /*保存保险主要信息*/
        vehiclePolicyMainMapper.insertVehiclePolicyMain(compulsoryVehiclePolicyMainInfo);

        //车船税信息
        VehicleTax vehicleTax = compulsoryPolicy.getVehicleTax();
        System.out.println(vehicleTax);
        vehicleTax.setPolicyId(compulsoryVehiclePolicyMainInfo.getId());
        vehicleTaxMapper.insertVehicleTax(vehicleTax);


        //修改商业保险的关联保单
        commercialVehiclePolicyMainInfo.setAssociatedPolicyId(compulsoryVehiclePolicyMainInfo.getId());
        vehiclePolicyMainMapper.updateVehiclePolicyMain(commercialVehiclePolicyMainInfo);

        /*交强险客户信息*/
        List<VehicleCustomer> compulsoryVehicleCustomerInfos = compulsoryPolicy.getVehicleCustomers();
        compulsoryVehicleCustomerInfos.forEach(vehicleCustomer -> vehicleCustomer.setPolicyId(compulsoryVehiclePolicyMainInfo.getId()));

        //添加险种责任信息对象
        for (VehicleCoverage vehicleCoverage : vehicleCoverages1) {
            vehicleCoverage.setPolicyId(compulsoryVehiclePolicyMainInfo.getId());
            vehicleCoverageMapper.insertVehicleCoverage(vehicleCoverage);
        }
        VehiclePremCalSub vehiclePremCalSub1 = compulsoryPolicy.getVehiclePremCalSub();
        vehiclePremCalSub1.setPolicyId(compulsoryVehiclePolicyMainInfo.getId());
        //保费计算辅助信息
        vehiclePremCalSubMapper.insertVehiclePremCalSub(vehiclePremCalSub1);

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
    //@GlobalTransactional用于开启全局事务，只有在整个事务的入口地方使用这个注解
    @GlobalTransactional(rollbackFor = RuntimeException.class)
    @Transactional(rollbackFor = RuntimeException.class)
    public int underwriting(Long id) {
        //这里只是传入了一个id,根据这个id,从t_vehicle_policy_main表找到policy对象，找到关联的另外一个policy对象以后，在进行以后的步骤
        System.out.println(id);
        ComBinedPolicy combinedPolicy = getCombinedPolicy(id);

        CompulsoryPolicy compulsoryPolicy = combinedPolicy.getCompulsoryPolicy();
        CommercialPolicy commercialPolicy = combinedPolicy.getCommercialPolicy();
        VehiclePremCalSub compulsoryVehiclePremCalSub = insurancePlatformApi.commercialPreConfirm(commercialPolicy);
        VehiclePremCalSub commercialVehiclePremCalSub1 = insurancePlatformApi.compulsoryPreConfirm(compulsoryPolicy);


        vehiclePremCalSubMapper.updateVehiclePremCalSub(compulsoryVehiclePremCalSub);
        vehiclePremCalSubMapper.updateVehiclePremCalSub(commercialVehiclePremCalSub1);

        //发送邮箱
/*        policyMessageCenterFegin.sendFinanceMessage(commercialPolicy.getVehiclePolicyMain().getDuePremium(),commercialPolicy.getVehiclePolicyMain().getId(),null);
        policyMessageCenterFegin.sendFinanceMessage(compulsoryPolicy.getVehiclePolicyMain().getDuePremium(),compulsoryPolicy.getVehiclePolicyMain().getId(),null);*/

        VehicleCollection vehicleCollection1 = new VehicleCollection();
        VehicleCollection vehicleCollection2 = new VehicleCollection();

        BeanUtils.copyProperties(commercialPolicy.getVehiclePolicyMain(), vehicleCollection1);
        BeanUtils.copyProperties(compulsoryPolicy.getVehiclePolicyMain(), vehicleCollection2);

        vehicleCollection1.setPolicyId(vehicleCollection1.getId());
        vehicleCollection2.setPolicyId(vehicleCollection2.getId());

        vehicleCollection1.setInsertTime(null);
        vehicleCollection1.setUpdateTime(null);
        vehicleCollection2.setInsertTime(null);
        vehicleCollection2.setUpdateTime(null);


        //调用财务服务成功预收费记录
        int underwriting1 = policyPayFeign.underwriting(vehicleCollection1);
        int underwriting = policyPayFeign.underwriting(vehicleCollection2);
        try {
            vehiclePolicyMainMapper.underwritingUpdate(id);
        } catch (Exception e) {
            return 0;
        }
        if (underwriting1 == 0 || underwriting == 0) {
            return 0;
        }

        return 1;
    }

    /**
     * 根据一个保单id给两个保单收费
     *
     * @param id
     * @return
     */
    @Override
    public String collect(Long id) {
        //这里只是传入了一个id,根据这个id,从t_vehicle_policy_main表找到policy对象，找到关联的另外一个policy对象以后，在进行以后的步骤
        System.out.println(id);
        ComBinedPolicy combinedPolicy = getCombinedPolicy(id);

        return policyPayFeign.collect(combinedPolicy);
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

/*    @RabbitListener(queues = "policy_main_queue")
    public void getQueue(Long id){
        ComBinedPolicy combinedPolicy = getCombinedPolicy(id);
    }*/

    /**
     * 查询待收费的保单列表
     *
     * @return
     */
    @Override
    public List<VehiclePolicyMain> queryCollect() {

        return vehiclePolicyMainMapper.queryCollect();
    }

    @Autowired
    PolicyMessageCenterFegin policyMessageCenterFegin;
    @Override
    public int insurancePolicy(Long id) {
        ComBinedPolicy combinedPolicy = getCombinedPolicy(id);
        VehiclePremCalSub vehiclePremCalSub = insurancePlatformApi.commercialQuery(combinedPolicy.getCommercialPolicy());
        VehiclePremCalSub vehiclePremCalSub1 = insurancePlatformApi.compulsoryQuery(combinedPolicy.getCompulsoryPolicy());

        vehiclePremCalSubMapper.updateVehiclePremCalSub(vehiclePremCalSub);
        vehiclePremCalSubMapper.updateVehiclePremCalSub(vehiclePremCalSub1);
        vehiclePolicyMainMapper.insurancePolicy(id);
        String email = "";
        List<VehicleCustomer> vehicleCustomers = combinedPolicy.getCommercialPolicy().getVehicleCustomers();
        for (VehicleCustomer vehicleCustomer : vehicleCustomers) {
            if(vehicleCustomer.getUserRoleType().equals("1"))
                email = vehicleCustomer.getEmail();
        }
        policyMessageCenterFegin.sendPolicyMainMessage(1,email);

        return 1;
    }
}
