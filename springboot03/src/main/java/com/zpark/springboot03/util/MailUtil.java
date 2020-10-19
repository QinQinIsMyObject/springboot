package com.zpark.springboot03.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

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
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //设置从哪儿发送(从哪儿来)
        mailMessage.setFrom("icaods@snapmail.cc");
        //设置发送给谁(到哪里去)
        mailMessage.setTo(to);
        //设置邮件的标题
        mailMessage.setSubject(title);
        //设置邮件的内容
        mailMessage.setText(content);
        //发送邮件
        javaMailSender.send(mailMessage);
    }

}
