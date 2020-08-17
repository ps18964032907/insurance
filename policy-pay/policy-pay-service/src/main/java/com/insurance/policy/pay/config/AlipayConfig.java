package com.insurance.policy.pay.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "pay")
public class AlipayConfig {
    public String app_id;
    // 商户私钥，您的PKCS8格式RSA2私钥
    public String merchant_private_key;
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public String alipay_public_key;

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public String notify_url;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public String return_url;

    // 签名方式
    public String sign_type;

    // 字符编码格式
    public String charset;

    // 支付宝网关
    public String gatewayUrl;

    public String getApp_id() {
        return app_id;
    }

    public String getMerchant_private_key() {
        return merchant_private_key;
    }

    public String getAlipay_public_key() {
        return alipay_public_key;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    public String getSign_type() {
        return sign_type;
    }

    public String getCharset() {
        return charset;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public void setMerchant_private_key(String merchant_private_key) {
        this.merchant_private_key = merchant_private_key;
    }

    public void setAlipay_public_key(String alipay_public_key) {
        this.alipay_public_key = alipay_public_key;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }
}
