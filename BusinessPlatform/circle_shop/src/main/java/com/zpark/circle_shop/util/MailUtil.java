package com.zpark.circle_shop.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Celery
 */
public class MailUtil {

    /**
     * 发送简单邮件的方法
     *
     * @param to             接收邮件方
     * @param title          邮件的标题
     * @param content        邮件的内容
     * @param javaMailSender 邮件发送器
     */
    public static void sendSimpleEmail(String to, String title, String content, JavaMailSender javaMailSender) {
        //简单的邮件信息对象
        SimpleMailMessage message = new SimpleMailMessage();
        //设置发送给谁
        message.setTo(to);
        //设置从哪里发送
        message.setFrom("liuzeyuan02@126.com");
        //设置邮件的标题
        message.setSubject(title);
        //设置邮件的内容
        message.setText(content);
        //发送邮件
        javaMailSender.send(message);
    }

    /**
     * 发送Html版本的验证码
     *
     * @param to             发送给谁
     * @param verifyCode     验证码内容
     * @param javaMailSender
     */
    public static void sendVerifyHtmlEmail(String to, String verifyCode, JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        //创建一个Thymeleaf的Context对象
        Context context = new Context();
        //设置参数
        context.setVariable("verifyCode", verifyCode);
        //生成一个字符串类型的内容（将模板页面和上下文对象绑定）
        String content = templateEngine.process("verifyPicTemplate.html", context);

        //准备一个邮件信息对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //设置从哪里发送
            helper.setFrom("liuzeyuan02@126.com");
            //设置发送给谁
            helper.setTo(to);
            //设置标题
            helper.setSubject("验证码");
            //设置内容
            helper.setText(content, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
