package com.insurance.policy.message.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

/**
 * @Author sjh
 * @creat 2020/8/16 14:20
 */
@Configuration
public class MailUtil {
    public  void sendFinaceEmail(BigDecimal duePremium,long id, String to) {
        HtmlEmail email = new HtmlEmail();//创建一个HtmlEmail实例对象
        email.setHostName("smtp.163.com");//邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
        email.setCharset("utf-8");//设置发送的字符类型
        try {
            email.addTo(to);//设置收件人
            email.setFrom("sjhxincg123@163.com", "woniu-18");//发送人的邮箱为自己的，用户名可以随便填
            email.setAuthentication("sjhxincg123@163.com", "UGKZNHDOFJXFITIW");//设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
            email.setSubject("蜗牛车险温馨提示");//设置发送主题
            email.setMsg("您的车险账单已经生成，保单号为"+id+"应缴金额为"+duePremium+"元，请尽快缴费，超时保单将会自动销毁，祝您生活愉快");//设置发送内容
            //由于邮件滥发等原因阿里云服务器禁用了25端口，所以这里得使用ssl加密传输（这样使用的端口号是465）
            email.setSSLOnConnect(true);
            email.send(); //发送邮件
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }



    public  void sendPolicyEmail(long id, String to) {
        HtmlEmail email = new HtmlEmail();//创建一个HtmlEmail实例对象
        email.setHostName("smtp.163.com");//邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
        email.setCharset("utf-8");//设置发送的字符类型
        try {
            email.addTo(to);//设置收件人
            email.setFrom("sjhxincg123@163.com", "woniu-18");//发送人的邮箱为自己的，用户名可以随便填
            email.setAuthentication("sjhxincg123@163.com", "UGKZNHDOFJXFITIW");//设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
            email.setSubject("蜗牛车险温馨提示");//设置发送主题
            email.setMsg("您的车险保单已经生成，保单号为"+id+"感谢您的投保，祝您生活愉快");//设置发送内容
            //由于邮件滥发等原因阿里云服务器禁用了25端口，所以这里得使用ssl加密传输（这样使用的端口号是465）
            email.setSSLOnConnect(true);
            email.send(); //发送邮件
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
