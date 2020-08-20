package com.insurance.policy.pay.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/8/17 19:37
 */
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2016102200740970";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQClsxdnLiS/BGy3nECvpoWoVz+bmbbmfvl49inu37NoBsCxoePizIV2Cw0KppzQOUl66kqcNZOYKLds8K+i8/V3NPVFh/sXUhaVqe4R5GdpZJq08Wu7I3pcMrd7unSes+281XKk8jr/fEI13a+VmscMkxpvz4eVf6EhhQzviN/l6pC+YQWCp2CkhI6HAiDE795wvee1RCnfx1sac3mrsBQJCNbBdcGJ7RvnQwiedEvvQkjVl3k1PUGh219sy9mYtaKYx5GWEuqsyzitYY4f6gfJdKZExdyJWPh6boyShJWFcXYg8mzbuXzF0gPNk+41I4vCwPZQgJswgZLofiGvl1lzAgMBAAECggEAcb/2KvD0bOVetNVhWrPYQW5Tzj/B3Y4BONbUhpWvFZS9J7RZOtLoM9b2Rv+F2zPYi7bXLVFiVyxsby7zg8wyEHmcfvUQnDvQIUIcms/PZfyYb+rj5/48+5odd6TSdjhS1jU5lJ5tfgIReeULqoosZyd9GJDBJRIOzXIHJVEzUyOORaWjtBHvBmrifxymvP8uTHpgWEk2yu+shHEIe8c6ti3eBApqIKfWZ8I3jmA4k54WBE1wD4uyWBs2dbRQyKxodGRiKLgSckVX9/jEdzFX8xnCnbCqUx9GrDVVdMvADeY/fuNP/N54M8kuboABPvLFUN3ltW/MmannhSVOLI9lQQKBgQDeiS1F5+g1lvKiL9Jpi/Ki98wII2L3Tvd3fa/CfmUYAdmT4U+WOjGsoZYN/sXxd7qWeLvl7sI/ddfH5Hh9cgyAN+3pIQJ+rz2U523RJTdW5jkTpanfp+1qQLjLj4DXabAOgFLHvn1G1dXWyNxkjenEZ1bA0q5f3kviOR1XDXqYYwKBgQC+neyaO7NTkJ1gwtlUIuObnX6Y9nBGpqvFaIr30uaYlDTBvmdvLXGJSjOW05OXxgcUHgqhe9UvyQrCH7DP8KSZN+NCh2tVHTP+5jYJkaird9O3uXV1rwfJHC2UQJYrDdjXHSRv0bZ/U9y1Q+jIICztylrGI5QD760Y1OfYcyAfsQKBgEGFYi8S+OvOZeIa8njhG6+gBhLfWGQ4e+RSvU0mqd1Hrig8TpKMz9NkQCoeuWk/N1DWe3xXsBm/OKlCO7ezvLqDvSjG71Udg6Qz/8F0k7aiUmvWew2/A8glBkPpHKZQF+YtIjJtg7U3gJ6cxUdWAV4yjvh3D6Bqcy1IY7sd3NrBAoGBAK9qt+1r2WiioiIEzmmePeXcdz+fMs3h3huqTJEqNeneIfrWFWv73wsgDP/VL3Mx2xvJVbEsO5FhKxRgg5+o7mDOTMbRxpWqbUo7YylFMXqyqFTgSFcHv1t1IgDXuxpeANpdyEVbMd5VN7xchWF+MrqWSRkkbrd8nshGTiIkOEDRAoGAQQecMZx2Fht7JrY5OXyY1hwltt+oBw8zvyqeINL0RhZ2B54SvxxdsABUREXf6qk5+StTtgqrNw8wgurtvO5Eh/eO+qwlsXQEv51qH1r19rY2VNEjfJt0cqZer7RPOD17rdHRKZ2V+h+TKjT1Z41W8Qw5Y84I+j4LCEH+LrFL7TE=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwbjGxh7t2QO9TJpS7pW9LHh8hOWLGVd37VFW6W9teSAJ4zt+Mfc7Ci/SpN/Q9BWYHZgMljABRtFM2jQMdQrwxPMFQd+HtbkN/zIIIYckEYIYE+gHPEp7pMTEPyki0CGdj47kB6RmhB1cctPfStzM8wLRlKv8USQLmtQQRrzYEdl7d7cwBOYBj2fLQJrcLXxku1RYd7Q5uNMe1avTZFnACqgq3qqz6sZFf4xiSZz/JvlPQpPeT0uUyKkQ9CJF7Hhww+dgvRpR7IsIcRU/MiQw6oXOrdBxhMp3+IO8ULa+sZHdb6AM/nAO/bmTGeyX+Y3Dxg/kPFP3F0dDRnsXFrkTuQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:20001/pay/updateCollect";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:20001/pay/updateCollect";

    // 签名方式
    public static String SIGN_TYPE = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String GATEWAYURL = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

