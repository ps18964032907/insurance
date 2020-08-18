package com.insurance.policy.pay.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.insurance.policy.admin.domain.VehiclePolicyMain;
import com.insurance.policy.pay.config.AlipayConfig;
import com.insurance.policy.pay.mapper.VehiclePayMapper;
import com.insurance.policy.pay.service.VehiclePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author jiangshuai
 * @date 2020/8/15 0015 10:32
 */
public class VehiclePolicyServiceImpl implements VehiclePolicyService {

    @Autowired
    VehiclePayMapper vehiclePayMapper;

    @Autowired
    AlipayConfig alipayConfig;

    @Override
    public VehiclePolicyMain selectVehiclePolicyByPolicyNo(String policyNo) {
        return vehiclePayMapper.selectVehiclePolicyByPolicyNo(policyNo);
    }

    @Override
    public int updateVehiclePolicyStatues(String policyNo) {
        return vehiclePayMapper.updateVehiclePolicyStatues(policyNo);
    }

    @Override
    public String payMoney(String policyNo, String name, String amount) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, alipayConfig.app_id,
                alipayConfig.merchant_private_key, "json", alipayConfig.charset,
                alipayConfig.alipay_public_key, alipayConfig.sign_type);
        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(alipayConfig.return_url);
        alipayRequest.setNotifyUrl(alipayConfig.notify_url);
        String subject = new String("账单缴费");
        //商品描述，可以为空
        String body = "蜗牛保险";
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        // 付款金额，必填
        String totalamount = amount.replace(",", "");
        int total_amount = Integer.valueOf(totalamount);
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + policyNo + "\"," + "\"total_amount\":\"" + total_amount
                + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        // 请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        // System.out.println(result);
        return result;
    }

    @Override
    public String refund(String policyNo, BigDecimal amount) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, alipayConfig.app_id,
                alipayConfig.merchant_private_key, "json", alipayConfig.charset,
                alipayConfig.alipay_public_key, alipayConfig.sign_type);
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        alipayRequest.setBizContent("\"policyNo\":\"" + policyNo
                + "\"," + "\"amount\":\"" + amount + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        return result;
    }

    @Override
    public List<VehiclePolicyMain> policySelectByStatus() {
        return vehiclePayMapper.policySelectByStatus();
    }

    @Override
    public List<VehiclePolicyMain> refundSelect() {
        return vehiclePayMapper.refundSelect();
    }

    @Override
    public String policyFailure(String policyNo, String name, String amount)  throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, alipayConfig.app_id,
                alipayConfig.merchant_private_key, "json", alipayConfig.charset,
                alipayConfig.alipay_public_key, alipayConfig.sign_type);
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        alipayRequest.setBizContent("\"policyNo\":\"" + policyNo
                + "\"," + "\"amount\":\"" + amount + "\","
                + "\"name\":\"" + name + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        return result;
    }
}
