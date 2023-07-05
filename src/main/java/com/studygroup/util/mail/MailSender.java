package com.studygroup.util.mail;

import com.studygroup.config.MailConfig;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MailSender {

  private final MailConfig mailConfig;

  public void sendTokenToEmail(String token, String to, String subject, String URI) {

    Session session = Session.getInstance(mailConfig.setSMTP(), mailConfig.setAuth());
    try {
      MimeMessage msg = new MimeMessage(session);
      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
      msg.addHeader("format", "flowed");
      msg.addHeader("Content-Transfer-Encoding", "8bit");

      msg.setFrom(new InternetAddress(
          "studyGroupApplication@example.com",
          "NoReply"));

      msg.setSubject(subject, "UTF-8");

      msg.setText(
          token,
          "UTF-8");

      msg.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse(to,
              false));

      Transport.send(msg);


    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
