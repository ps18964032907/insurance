package com.insurance.policy.pay.controller;

import com.insurance.policy.admin.domain.ComBinedPolicy;
import com.insurance.policy.admin.domain.VehiclePolicyMain;
import com.insurance.policy.pay.domain.VehicleCollection;
import com.insurance.policy.pay.service.VehiclePolicyService;
import com.insurance.policy.premium.service.CalculatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author jiangshuai
 * @date 2020/8/13 0013 14:24
 */
@RestController
@RefreshScope//增加这个注解可以接受动态刷新配置文件的参数
@RequestMapping("/pay")
public class VehiclePayController {

    @Autowired
    VehiclePolicyService vehiclePolicyService;

    @Autowired
    CalculatedService calculatedService;
    /*
     * @Author jiangshuai
     * @Description //TODO 缴费
     * @Date 11:17 2020/8/18 0018
     * @Param
     * @since 1.0.0
     * @return
     **/
    @RequestMapping("/policyFailure/{policyNo}")
    public String policyFailure(@PathVariable("policyNo") String policyNo, String name, String amount) throws Exception {
        String pay = vehiclePolicyService.policyFailure(policyNo, name, amount);
        return pay;
    }

    /*
     * @Author jiangshuai
     * @Description //TODO 缴费
     * @Date 11:17 2020/8/18 0018
     * @Param
     * @since 1.0.0
     * @return
     **/
    @RequestMapping("/payMoney/{policyNo}")
    public String payMoney(@PathVariable("policyNo") String policyNo, String name, String amount) throws Exception {
        String pay = vehiclePolicyService.payMoney(policyNo, name, amount);
        return pay;
    }
/*
 * @Author jiangshuai
 * @Description //TODO 根据订单号查询
 * @Date 18:32 2020/8/18 0018
 * @Param
 * @since 1.0.0
 * @return
 **/
    @RequestMapping("/policySelectBypolicyNo/{policyNo}")
    public VehiclePolicyMain policySelectBypolicyNo(@PathVariable("policyNo") String policyNo) throws Exception {
        VehiclePolicyMain vehiclePolicyMain = vehiclePolicyService.selectVehiclePolicyByPolicyNo(policyNo);
        return vehiclePolicyMain;
    }
/*
 * @Author jiangshuai
 * @Description //TODO 查询缴费订单
 * @Date 18:33 2020/8/18 0018
 * @Param []
 * @since 1.0.0
 * @return java.util.List<com.insurance.policy.admin.domain.VehiclePolicyMain>
 **/
    @RequestMapping("/policySelectByStatus")
    public List<VehiclePolicyMain> policySelectByStatus() throws Exception {
        List<VehiclePolicyMain> vehiclePolicyMainList = vehiclePolicyService.policySelectByStatus();
        return vehiclePolicyMainList;
    }

    /*
     * @Author jiangshuai
     * @Description //TODO
     * @Date 11:17 2020/8/18 0018
     * @Param [policyNo, name, amount]
     * @since 1.0.0
     * @return java.lang.String
     **/
    @RequestMapping("/refund/{policyNo}")
    public String refund(@PathVariable("policyNo") String policyNo) throws Exception {
        VehiclePolicyMain vehiclePolicyMain = vehiclePolicyService.selectVehiclePolicyByPolicyNo(policyNo);
        BigDecimal amount = vehiclePolicyMain.getDuePremium();
        String pay = vehiclePolicyService.refund(policyNo, amount);
        return pay;
    }
/*
 * @Author jiangshuai
 * @Description //TODO 查询退款订单
 * @Date 18:34 2020/8/18 0018
 * @Param []
 * @since 1.0.0
 * @return java.util.List<com.insurance.policy.admin.domain.VehiclePolicyMain>
 **/
    @RequestMapping("/refundSelect")
    public List<VehiclePolicyMain> refundSelect() throws Exception {
        List<VehiclePolicyMain> refundList = vehiclePolicyService.refundSelect();
        return refundList;
    }
/*
 * @Author jiangshuai
 * @Description //TODO 支付成功后的提示语
 * @Date 18:35 2020/8/18 0018
 * @Param [policyNo]
 * @since 1.0.0
 * @return void
 **/
    @RequestMapping("/notify/{policyNo}")
    public void doNotify(@PathVariable("policyNo") String policyNo) {
        vehiclePolicyService.updateVehiclePolicyStatues(policyNo);
    }

    /*
     * @Author jiangshuai
     * @Description //TODO 返回ComBinedPolicy对象
     * @Date 11:18 2020/8/18 0018
     * @Param [comBinedPolicy]
     * @since 1.0.0
     * @return com.insurance.policy.admin.domain.ComBinedPolicy
     **/
    @RequestMapping("/pay/savePolicy")
    public ComBinedPolicy calculatePolicy(@RequestBody ComBinedPolicy comBinedPolicy) {
        try {
            return calculatedService.calculatedPremium(comBinedPolicy);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * @Author jiangshuai
     * @Description //TODO 支付成功之后修改保单状态
     * @Date 11:17 2020/8/18 0018
     * @Param [id]
     * @since 1.0.0
     * @return java.lang.String
     **/
    @RequestMapping("/return/{policyNo}")
    public String doReturn(@PathVariable("policyNo") String policyNo) {
        vehiclePolicyService.updateVehiclePolicyStatues(policyNo);
        return "redirect:/savePolicy?policyNo=" + policyNo;
    }


    /**
     * 缴费
     *
     * @param vehicleCollection 保单收费对象
     * @return 险种责任信息
     */
    @RequestMapping("/collect")
    public void collect(VehicleCollection vehicleCollection){

    }

    /**
     * 缴费
     *
     * @param vehicleCollection 保单收费对象
     * @return 险种责任信息
     */

    public void underwriting(VehicleCollection vehicleCollection){

    }
}
