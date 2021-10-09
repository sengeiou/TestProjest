package utils;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;

public class MailUtils {
    public static void main(String[] args) throws Exception {
        /**
         * 1.配置发件人邮箱信息以及创建一个Java 配置类存放SMTP服务器地址等参数。
         */
        String sendEmailAccount = "dnslin@outlook.lv";                            // 发件人邮箱
        String sendEmailPassword = "catchers.tech";                                        // 发件人密码
        String receiveMailAccount = "dnsline@qq.com";                                // 收件人邮箱
        Properties props = new Properties();
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.socketFactory.port", "587");
        props.setProperty("mail.smtp.host", "smtp.office365.com");
        props.setProperty("mail.transport.protocol", "smtp");

        props.setProperty("mail.smtp.auth", "true");
//        props.setProperty("mail.smtp.ssl.enable", "true");
        props.setProperty("mail.smtp.starttls.required", "true");
        props.setProperty("mail.smtp.starttls.Enable", "true");      // 使用Java配置类进行配置
        props.setProperty("mail.smtp.socketFactory.fallback", "false");


        /**
         * 2.创建一个同邮件服务器交互的session
         */
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);                                 // 1. 创建一封邮件
        message.setFrom(new InternetAddress(sendEmailAccount, "123", "UTF-8"));  // 2. From: 发件人
        message.setRecipient(MimeMessage.RecipientType.TO,
                new InternetAddress(receiveMailAccount, "爱你", "UTF-8"));         // 3. To: 收件人
        message.setSubject("垃圾", "UTF-8");                                                 // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setContent("<h3>This is a test email.</h3>", "text/html;charset=UTF-8"); // 5. Content: 邮件正文
        message.setSentDate(new Date());                                                 // 6. 设置邮件发件时间
        message.saveChanges();                                                         // 7. 保存设置

        /**
         * 3.创建一封格式化的邮件
         */
        Transport transport = session.getTransport();                                     // 1. 根据 Session 获取邮件传输对象
        transport.connect(sendEmailAccount, sendEmailPassword);                             // 2. 使用 邮箱账号 和 密码 连接邮件服务器
        transport.sendMessage(message, message.getAllRecipients());                         // 3. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人,
        transport.close();                                                                 // 4. 关闭连接
    }


}
