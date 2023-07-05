package com.studygroup.service.email;

import com.studygroup.domain.Member;
import com.studygroup.util.mail.MailSender;
import com.studygroup.util.constant.uri.EmailSentURI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("SendVerificationToken")

public class SendVerificationToken implements SendTokenToEmailService {


  private final MailSender mailSender;

  @Override
  public void sendTokenToEmail(Member member, String token) {

    mailSender.sendTokenToEmail(token,
        member.getEmail(),
        "회원가입 인증 이메일입니다.",
        EmailSentURI.EMAIL_VERIFICATION_LINK);

  }
}
