package com.studygroup.service.email;

import com.studygroup.domain.Member;
import com.studygroup.repository.UserRepository;
import com.studygroup.util.mail.MailSender;
import com.studygroup.util.constant.uri.EmailSentURI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("SendPasswordResetToken")
@RequiredArgsConstructor
public class SendPasswordResetToken implements SendTokenToEmailService {

  private final MailSender mailSender;
  private final UserRepository userRepo;


  @Override
  public void sendTokenToEmail(Member member, String token) {

    mailSender.sendTokenToEmail(
        token,
        member.getEmail(),
        "패스워드 리셋 이메일",
        EmailSentURI.EMAIL_PASSWORD_LINK);


  }
}
