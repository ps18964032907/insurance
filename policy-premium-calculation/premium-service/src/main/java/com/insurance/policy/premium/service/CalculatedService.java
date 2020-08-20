package com.insurance.policy.premium.service;

import com.insurance.policy.admin.domain.ComBinedPolicy;
import com.insurance.policy.admin.domain.VehicleCoverage;
import com.insurance.policy.admin.domain.VehiclePremCalSub;
import com.insurance.policy.admin.domain.VehicleTax;
import com.insurance.policy.premium.connection.*;
import com.insurance.policy.premium.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author mhd
 * @className CalculatedService
 * @description TODO
 * @create 2020/8/14
 * @since 1.0.0
 */
@Service
public class CalculatedService {
    private static final BigDecimal  STANDPREMIUMCMP = new BigDecimal(2000);
    private static final BigDecimal  STANDPREMIUMCPP = new BigDecimal(1000);


    @Autowired
    ThreadPoolTaskExecutor taskExector;


    /**
     * 计算保费
     *
     * @param comBinedPolicy
     * @return
     */
    public ComBinedPolicy calculatedPremium(ComBinedPolicy comBinedPolicy) throws ExecutionException, InterruptedException {

        //添加监管平台商业险连接
        Future<VehiclePremCalSub> vehiclePremCalSubForCMPFuture = taskExector.submit(new IndustryPlatformConnectionForCMP(comBinedPolicy.getCommercialPolicy()));
        //添加监管平台交强险连接
        Future<VehiclePremCalSub> vehiclePremCalSubForCPPFuture = taskExector.submit(new IndustryPlatformConnectionForCPP(comBinedPolicy.getCompulsoryPolicy()));
        //添加车船税平台连接
        Future<VehicleTax> vehicleTaxFuture = taskExector.submit(new VehicleTaxPlatformConnection(comBinedPolicy.getCompulsoryPolicy().getVehicleInsured()));
        //添加车辆指标系统连接
        Future<BigDecimal> vehicleIndexCoefficientFuture = taskExector.submit(new VehicleStatisticsConnection(comBinedPolicy.getCompulsoryPolicy().getVehicleInsured()));
        //添加客户指标系统连接
//        List<VehicleCustomer> vehicleCustomers = comBinedPolicy.getCompulsoryPolicy().getVehicleCustomers();
//        List<VehicleCustomer> collect = vehicleCustomers.stream().filter(VehicleCustomer -> (VehicleCustomer.getUserRoleType().equals("1")))
//                .collect(Collectors.toList());
//        VehicleCustomer vehicleCustomer = collect.get(0);
        Future<BigDecimal> customerIndexCoefficientFuture = taskExector.submit(new CustomerStatisticsConnection(comBinedPolicy.getCompulsoryPolicy().getVehicleCustomers().stream()
                .filter(VehicleCustomer -> (VehicleCustomer.getUserRoleType().equals("1")))
                .collect(Collectors.toList()).get(0)));



        final VehiclePremCalSub vehiclePremCalSubForCMP = vehiclePremCalSubForCMPFuture.get();
        final VehiclePremCalSub vehiclePremCalSubForCPP = vehiclePremCalSubForCPPFuture.get();
        final VehicleTax vehicleTax = vehicleTaxFuture.get();
        //车辆指标优惠系数
        final BigDecimal vehicleIndexCoefficient = vehicleIndexCoefficientFuture.get();
        //客户指标优惠系数
        final BigDecimal customerIndexCoefficient = customerIndexCoefficientFuture.get();

        //计算保费
        //商业险
        //计算最终的优惠系数=无赔款优待系数*交通违法系数*客户指标系数*车辆指标系数
        BigDecimal finalDiscountRate =vehiclePremCalSubForCMP.getNcdRate().multiply(vehiclePremCalSubForCMP.getTrafficViolationRate().multiply(customerIndexCoefficient.multiply(vehicleIndexCoefficient)));
        //把最终优惠系数设置到uwrate字段
        vehiclePremCalSubForCMP.setUwRate(finalDiscountRate);
        //车损险保费=平台返回的基础纯风险保费*最终的优惠系数/0.65
        BigDecimal vehicleDamageInsurancePremium = vehiclePremCalSubForCMP.getPlatformBenchRiskPrem().multiply(finalDiscountRate).divide(new BigDecimal(0.65), 2, BigDecimal.ROUND_HALF_UP);
        VehicleCoverage vehicleDamageInsurance =comBinedPolicy.getCommercialPolicy().getVehicleCoverages().stream().filter(VehicleCoverage->(VehicleCoverage.getProductionCoverageCode().equals("A1"))).collect(Collectors.toList()).get(0);
        //标准保费2000
        vehicleDamageInsurance.setStandPremium(STANDPREMIUMCMP);
        //应缴保费
        vehicleDamageInsurance.setDuePremium(vehicleDamageInsurancePremium);

        //第三者责任险保费=第三者责任险保额*0.0015
        VehicleCoverage thirdPartyLiabilityInsurance = comBinedPolicy.getCommercialPolicy().getVehicleCoverages().stream().filter(VehicleCoverage->(VehicleCoverage.getProductionCoverageCode().equals("A3"))).collect(Collectors.toList()).get(0);
        BigDecimal thirdPartyLiabilityInsurancePremium = thirdPartyLiabilityInsurance.getSumInsured().multiply(new BigDecimal(0.0015));
        //标准保费2000
        thirdPartyLiabilityInsurance.setStandPremium(STANDPREMIUMCMP);
        //应缴保费
        thirdPartyLiabilityInsurance.setDuePremium(thirdPartyLiabilityInsurancePremium);

        //折旧的月数=当前系统时间-车辆初登日期（这个要换算成月数）
        Date vehicleFirstRegDate =  comBinedPolicy.getCommercialPolicy().getVehicleInsured().getVehicleFirstRegDate();
        int monthDiff = DateUtil.getMonthDiff(new Date(), vehicleFirstRegDate);

        //全车盗抢险保费=车辆新车购置价*0.9的N次方【N就代表折旧的月数】
        BigDecimal theftRobberyPremium = comBinedPolicy.getCommercialPolicy().getVehicleInsured().getNewVehiclePrice().multiply(new  BigDecimal(0.9).pow(monthDiff));
        VehicleCoverage theftRobbery = comBinedPolicy.getCommercialPolicy().getVehicleCoverages().stream().filter(VehicleCoverage->(VehicleCoverage.getProductionCoverageCode().equals("A2"))).collect(Collectors.toList()).get(0);
        //应缴保费
        theftRobbery.setDuePremium(theftRobberyPremium);
        //标准保费2000
        theftRobbery.setStandPremium(STANDPREMIUMCMP);

        //Compulsory Traffic Accident Liability Insurance交强险
        //计算最终的优惠系数=无赔款优待系数*交通违法系数
        BigDecimal finalDiscountRate2 =vehiclePremCalSubForCPP.getNcdRate().multiply(vehiclePremCalSubForCPP.getTrafficViolationRate());
        vehiclePremCalSubForCPP.setUwRate(finalDiscountRate2);
        // 保费=1000*优惠系数
        BigDecimal compulsoryInsurancePremium =  STANDPREMIUMCPP.multiply(finalDiscountRate2);
        //标准保费1000
        comBinedPolicy.getCompulsoryPolicy().getVehicleCoverages().get(0).setStandPremium(STANDPREMIUMCPP);
        comBinedPolicy.getCompulsoryPolicy().getVehicleCoverages().get(0).setDuePremium(compulsoryInsurancePremium);


        //设置商业险总标准保费
        BigDecimal finalStandPremium = STANDPREMIUMCMP.multiply(new BigDecimal(comBinedPolicy.getCommercialPolicy().getVehicleCoverages().size()));
        comBinedPolicy.getCommercialPolicy().getVehiclePolicyMain().setStandPremium(finalStandPremium);
        //设置商业险总应缴保费保费
        BigDecimal finalDuePremium = vehicleDamageInsurancePremium.add(theftRobberyPremium.add(thirdPartyLiabilityInsurancePremium));
        comBinedPolicy.getCommercialPolicy().getVehiclePolicyMain().setDuePremium(finalDuePremium);
        //设置交强险总标准保费
        comBinedPolicy.getCompulsoryPolicy().getVehiclePolicyMain().setStandPremium(STANDPREMIUMCPP);
        //设置交强险总应缴保费保费
        comBinedPolicy.getCompulsoryPolicy().getVehiclePolicyMain().setDuePremium(compulsoryInsurancePremium);


        comBinedPolicy.getCompulsoryPolicy().setVehicleTax(vehicleTax);
        comBinedPolicy.getCompulsoryPolicy().setVehiclePremCalSub(vehiclePremCalSubForCPP);
        comBinedPolicy.getCommercialPolicy().setVehiclePremCalSub(vehiclePremCalSubForCMP);
        return comBinedPolicy;
    }


}
