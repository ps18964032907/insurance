package com.insurance.policy.pay.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "pay")
public class AlipayConfig {
    public String app_id = "2016101800717204";
    // 商户私钥，您的PKCS8格式RSA2私钥
    public String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDMj2kchPtIGXwUHko8hs+gRZ9k6cXRG3ZyEN3V227HPsizVHE5TkI5WTRhE8ePGaw64J2LvhlQRBJzKLiqwoHptxYHlVIGR53NK8iCYI7NDoiGM4iyJNhXRo773EL+VqMIk7ZCv/O09UhGg2a6ZzMgZnl+yKEEIpj7p8j63dBb8ilGpqOarkx9hpzZXAmKikNF3QJKdLORZeGdginMu7b94V5FSJBOuzeYS4S4T0odIR5a5ZxOeQqRYOVMOKi9pQIKhhb7Rq/ZxvYH3dF2oj+suTGOZNaUkkOpVkac8cf1NcfSlP7ZvUWrEbbTK36nvEdrGAPxgBO1OHo4dZm9mXxNAgMBAAECggEAJuaS16CWZLSr+utztKTycQkscbVfMA/n63M3rIapP2y6IBhL29j9EQjcxCVNyvHX5e53J28EQ1Zep9WzoI2SxQ5TOyfzTExsPus3m0uffHFqFxNPyIjnN1mssUrG1rVFFcl5XKJCR0Rp9Tbh30rm1x+FtU2UCURHEXxr5QEvsXw5PEZy5FFBADdfLv34lTNjC0B3IvsCgcl3tMGMk3nTLYLuyfSlzCAR+UhzSW8m/nrVCHYf5up7ZPfIjtwMF1NtYfUU425J4nIw445NO7oV2cWxjU4xS1iz5OblqVkt/R34cJif/BDXMuPtC2w/mdVXOlGEsR/Pidc9Q1ZAt/OqxQKBgQD/TRzbGgzQxUbD4Z50mSHYtOpRTI/lhZzVuM6Cn2URHz/JLIfcsAwjz3dr+S2oJcCE6o+cmZ8+eELi2VX8ot/AKS1IbH+a7uZotjioeYpmY+RUoXKwO3B4hF3g0KQxbfPZzbP6WImO0j67IY6GEx9/knv7Euq2f7JjyM8m6VzdNwKBgQDNHr58yztjkxqekBFlok5Ny8QzFoorOSkVyhif8jv+OrFaAMsPLMRwC+fyqpTg2WlxIbkZytzUt0xb/lMSkzDLX1rs/RlCTC1oqKbuUtLsKhdeEMt6zQX0r5//vw656f99R42W58QMjHiv6WAbrt1hVYC3FVW8UfTxt8dlFULUmwKBgAfad2jGBF7nsqWT6yvrCf+AkTSvBj4pPgnVBYU/IfzE2VF27Tb8s2LmnuXTZHQntSRhkslecGebi3Mu/FYpLHjcy2fO7p31e2D9OgD0vC0qJXv0+3IwYeVTLRAdWu+qUjYA/ehVkOWz0fovrhKxw+ZIu0ed7Vpv0yz+oGCyDQlBAoGAXKvcMfN75HPsneDHC1z19a3u+YngaMDNcBu7XmAJfMW1pM0g8L0wDtNCOt+YnbF8MvGieCWv+abKp/FSRN0dOXTeVp3O0KYsCdHQgXIH+0zU+9mopNWGMFM8fUYreBFhLY+rR5d0eproV8mfc0JgoH4Jxi2sa4W3nZGDU4b0h6sCgYBgoHYjbW+SpREtbksFZmgP+5kzDqNudS5lxDbczW78bNqFMZYjTnyzbLUxbuTZ6HuH+95jtoPTJP3o8QxkyCKewcKyj5oTclLRlckiZAHPQ8kmu7frhMIaYD1bC6ZhEex27rfTKXErvsBqh0ZqNQkGzqnBqW92rywE3MGTjvN1Ew==";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6JKxx7glBBq1kjdDBcpL3isxik33+m2L2psyvG0BXiR4QnPI0slYsPgjFdajQ3CgybbkY7iyjy0RF4IXOmb+/ZjJueXAKZ02PL8EtZChdNn0ufwIjCNgikPAEfymrPQIFzCJ2tMRllnjtkbJQ5xNmp3KiElLHslDwK8yyCpV4oCO+dwv6ki7mxW0qsk7rriRi0QqLNkvyNFwQOcTKJNQxws4F4ICzBBklJrVK66KXiMTPQ+pfze750JErp7aXrMPL6mu91OW7LWs5C3pLULAEopMNNPEYj667QUsi/ChKQVBUOZaBWIDwQ9bf6CirFoo2YkkGyxSquQBtip3qoJ5xwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public String notify_url = "localhost/insurance/notify";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public String return_url = "localhost/insurance/return";

    // 签名方式
    public String sign_type = "RSA2";

    // 字符编码格式
    public String charset = "utf-8";

    // 支付宝网关
    public String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

}
