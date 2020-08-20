package com.insurance.policy.pay.controller;

import cn.hutool.http.HttpResponse;
import com.insurance.policy.admin.domain.ComBinedPolicy;
import com.insurance.policy.pay.domain.VehicleCollection;
import com.insurance.policy.pay.service.AlipayService;
import com.insurance.policy.pay.service.VehicleCollectionService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author jiangshuai
 * @date 2020/8/13 0013 14:24
 */
@RestController
@RefreshScope//增加这个注解可以接受动态刷新配置文件的参数
@RequestMapping("/pay")
public class VehiclePayController {


    @Autowired
    VehicleCollectionService vehicleCollectionService;

    @Autowired
    AlipayService alipayService;
    /**
     * 核单
     *
     * @param vehicleCollection 保单收费对象
     * @return 险种责任信息
     */
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/underwriting")
    public int underwriting(@RequestBody VehicleCollection vehicleCollection){
        return  vehicleCollectionService.underwriting(vehicleCollection);
    }


    @RequestMapping("/updateCollect")
    public void updateCollect(Long out_trade_no, HttpServletResponse response) throws IOException {
        System.out.println(out_trade_no);
        rabbitTemplate.convertAndSend("createMain",out_trade_no);
        response.sendRedirect("/");
    }

    /**
     * 缴费
     *
     * @param comBinedPolicy 交强险和商业险组合对象
     * @return 险种责任信息
     */
    
    @RequestMapping("/collect")
    public String collect(@RequestBody ComBinedPolicy comBinedPolicy) throws Exception {
        BigDecimal duePremium =
                comBinedPolicy.getCommercialPolicy().getVehiclePolicyMain().getDuePremium().add(comBinedPolicy.getCompulsoryPolicy().getVehiclePolicyMain().getDuePremium());
         return alipayService.webPagePay(comBinedPolicy.getCommercialPolicy().getVehiclePolicyMain().getId()+"",duePremium,"我们是骗子");

    }
}
