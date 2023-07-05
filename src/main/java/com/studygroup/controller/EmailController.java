package com.studygroup.controller;

import com.studygroup.domain.Member;
import com.studygroup.service.email.CheckTokenAlreadySent;
import com.studygroup.service.email.SaveTheToken;
import com.studygroup.service.email.SendTokenToEmailService;
import com.studygroup.service.user.RetrieveMemberByEmail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmailController {

  private final RetrieveMemberByEmail retrieveMemberByEmail;
  private final SendTokenToEmailService sendPasswordResetToken;
  private final SendTokenToEmailService sendVerificationToken;
  private final SaveTheToken saveTheVerificationToken;
  private final SaveTheToken saveThePasswordResetToken;
  private final CheckTokenAlreadySent checkPasswordResetTokenSent;
  private final CheckTokenAlreadySent checkVerificationTokenSent;

  public EmailController(
      @Qualifier("RetrieveMemberByEmailService") RetrieveMemberByEmail retrieveMemberByEmail,
      @Qualifier("SendPasswordResetToken") SendTokenToEmailService sendPasswordResetToken,
      @Qualifier("SendVerificationToken") SendTokenToEmailService sendVerificationToken,
      @Qualifier("SaveTheVerificationToken") SaveTheToken saveTheVerificationToken,
      @Qualifier("SaveThePasswordResetToken") SaveTheToken saveThePasswordResetToken,
      @Qualifier("CheckResetPasswordTokenAlreadySent") CheckTokenAlreadySent checkPasswordResetTokenSent,
      @Qualifier("CheckVerificationTokenAlreadySent") CheckTokenAlreadySent checkVerificationTokenSent) {

    this.retrieveMemberByEmail = retrieveMemberByEmail;
    this.sendPasswordResetToken = sendPasswordResetToken;
    this.sendVerificationToken = sendVerificationToken;
    this.saveTheVerificationToken = saveTheVerificationToken;
    this.saveThePasswordResetToken = saveThePasswordResetToken;
    this.checkPasswordResetTokenSent = checkPasswordResetTokenSent;
    this.checkVerificationTokenSent = checkVerificationTokenSent;
  }

    /*

    sequence
    1. 같은 유저의 이메일 인증 이메일이 있는지 확인 있다면 삭제 (최근 발송된 것만을 위한 이메일 인증을 위한 로직)
    2. 토큰 저장
    3. 토큰 저장을 통해 얻은 TokenData String으로 유저 이메일로 토큰을 send
     */

  @PostMapping("/api/auth/send/{email}/verification-token")
  public ResponseEntity<Object> sendVerificationEmailToUser(@PathVariable String email) {

    Member member = retrieveMemberByEmail.getMember(email);
    checkVerificationTokenSent.checkTokenSentIfSoDelete(member);
    String token = saveTheVerificationToken.save(member);
    sendVerificationToken.sendTokenToEmail(member, token);

    return ResponseEntity.
        status(HttpStatus.OK).
        body("verification mail was sent");
  }


  @PostMapping("/api/auth/send/{email}/password-reset-token")
  public ResponseEntity<Object> sendPasswordResetTokenToUser(@PathVariable String email) {

    Member member = retrieveMemberByEmail.getMember(email);
    checkPasswordResetTokenSent.checkTokenSentIfSoDelete(member);
    String token = saveThePasswordResetToken.save(member);
    sendPasswordResetToken.sendTokenToEmail(member, token);

    return ResponseEntity.
        status(HttpStatus.OK).
        body("Password reset mail was sent");

  }
}
