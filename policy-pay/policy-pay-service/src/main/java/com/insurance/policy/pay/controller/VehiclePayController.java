package com.insurance.policy.pay.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.insurance.policy.admin.domain.VehiclePolicyMain;
import com.insurance.policy.pay.config.AlipayConfig;
import com.insurance.policy.pay.service.VehiclePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jiangshuai
 * @date 2020/8/13 0013 14:24
 */
@RestController
@RefreshScope//增加这个注解可以接受动态刷新配置文件的参数
@RequestMapping("/pay")
@EnableConfigurationProperties(AlipayConfig.class)
public class VehiclePayController {

    @Autowired
    AlipayConfig alipayConfig;

    @Autowired
    VehiclePolicyService vehiclePolicyService;

    @RequestMapping("/payMoney/{id}")
    public void payMoney(HttpServletResponse httpResponse, @PathVariable("id") long id) throws IOException {
        /*  String object = restTemplate.getForObject("http://policy-pay-service/pay/payMoney", String.class);*/
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, alipayConfig.app_id, alipayConfig.merchant_private_key,
                "json", alipayConfig.charset, alipayConfig.alipay_public_key, alipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(alipayConfig.return_url);
        alipayRequest.setNotifyUrl(alipayConfig.notify_url);
//        //商品价格总额
//        String orderNo = (String) session.getAttribute("orderNo");
      /*  if (id ) {
            return;
        }*/
        VehiclePolicyMain vehiclePolicyMain = this.vehiclePolicyService.selectVehiclePolicyById(id);
        if (vehiclePolicyService == null) {
            return;
        }
        //商品名称
        String subject = new String("账单缴费");
        //商品描述，可以为空
        String body = "蜗牛保险";
        //填充业务参数
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);
        //model.setOutTradeNo(policyNo);
        model.setTotalAmount(String.valueOf(vehiclePolicyMain.getDuePremium()));
        model.setSubject(subject);
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        alipayRequest.setBizModel(model);
        alipayRequest.setReturnUrl(alipayConfig.return_url);
        alipayRequest.setNotifyUrl(alipayConfig.notify_url);
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (Exception e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=utf-8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @RequestMapping("/notify/{id}")
    public void doNotify(@PathVariable("id") long id) {
        vehiclePolicyService.updateVehiclePolicyStatues(id);
    }

    @RequestMapping("/return/{id}")
    public String doReturn(@PathVariable("id") long id) {
        vehiclePolicyService.updateVehiclePolicyStatues(id);
        return "redirect:/savePolicy?id=" + id;
    }
}
