package com.studygroup.config;

import com.studygroup.util.constant.SmtpEmailAccountInfo;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

  @Bean
  public Authenticator setAuth() {
    return new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(SmtpEmailAccountInfo.email,
            SmtpEmailAccountInfo.emailPassword);
      }
    };
  }

  @Bean
  public Properties setSMTP() {
    Properties props = new Properties();
    props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
    props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
    props.put("mail.smtp.port", "587"); //TLS Port
    props.put("mail.smtp.auth", "true"); //enable authentication
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); //ssl trust
    props.put("mail.smtp.ssl.protocols", "TLSv1.2"); //ssl trust
    props.put("mail.smtp.ssl.enable", "false");
    return props;
  }

  @Bean
  public SimpleMailMessage simpleMailMessage() {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setSubject("Complete Registration!");
    mailMessage.setFrom("rnwldnd7248@gmail.com");
    return mailMessage;
  }

  @Bean
  public JavaMailSender javaMailSender() {
    return new JavaMailSenderImpl();
  }

}
