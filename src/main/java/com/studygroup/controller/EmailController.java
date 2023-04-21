package com.studygroup.controller;

import com.studygroup.config.MailConfig;
import com.studygroup.entity.Member;
import com.studygroup.repository.EmailRepository;
import com.studygroup.repository.UserRepository;
import com.studygroup.service.email.*;
import com.studygroup.service.user.UserService;
import com.studygroup.util.MailSender;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmailController {


    private final MailSender mailSender;
    private final MailConfig mailConfig;
    private final EmailRepository emailRepository;
    private final UserRepository userRepository;;
    private final UserService userService;
    private final SendTokenToEmailService sendPasswordResetToken;
    private final SendTokenToEmailService sendVerificationToken;
    private final SaveTheToken saveTheVerificationToken;
    private final SaveTheToken saveThePasswordResetToken;
    private final CheckTokenAlreadySent checkPasswordResetTokenSent;
    private final CheckTokenAlreadySent checkVerificationTokenSent;

    @Autowired
    public EmailController(MailSender mailSender,
                           MailConfig mailConfig,
                           UserRepository userRepository,
                           EmailRepository emailRepository,
                           UserService userService) {

        this.mailSender = mailSender;
        this.mailConfig = mailConfig;
        this.userRepository = userRepository;
        this.emailRepository = emailRepository;
        this.userService = userService;
        this.sendPasswordResetToken = new SendPasswordResetToken(mailSender, userRepository);
        this.sendVerificationToken = new SendVerificationToken(mailSender, userRepository);
        this.saveTheVerificationToken = new SaveTheVerificationToken(emailRepository, userRepository);
        this.saveThePasswordResetToken = new SaveThePasswordResetToken(emailRepository, userRepository);
        this.checkPasswordResetTokenSent = new CheckResetPasswordTokenAlreadySent(emailRepository);
        this.checkVerificationTokenSent = new CheckVerificationTokenAlreadySent(emailRepository);
    }

    /*

    sequence
    1. 같은 유저의 이메일 인증 이메일이 있는지 확인 있다면 삭제 (최근 발송된 것만을 위한 이메일 인증을 위한 로직)
    2. 토큰 저장
    3. 토큰 저장을 통해 얻은 Token String으로 유저 이메일로 토큰을 send
     */

    @PostMapping("/api/user/email/verification/user-email")
    public ResponseEntity<Object> sendVerificationEmailToUser(@AuthenticationPrincipal Authentication authResult){

        Long memberId = ((Member)authResult.getPrincipal()).getId();
        checkVerificationTokenSent.checkTokenSentIfSoDelete(memberId);
        String token = saveTheVerificationToken.save(memberId);
        sendVerificationToken.sendTokenToEmail(memberId, token);

        return ResponseEntity.status(HttpStatus.OK).body("verification mail was sent");
    }

     /*

    sequence
    1. check the user-email is existed
    2.
    3.
     */

    @PostMapping("/api/email/password-reset/user-email")
    public ResponseEntity<Object> sendPasswordResetTokenToUser(@RequestParam String memberEmail) {

        Long memberId = userService.checkMemberIsExitedByEmailBeforeSendResetPassword(memberEmail);
        checkPasswordResetTokenSent.checkTokenSentIfSoDelete(memberId);
        String token = saveTheVerificationToken.save(memberId);
        sendPasswordResetToken.sendTokenToEmail(memberId, token);

        return ResponseEntity.status(HttpStatus.OK).body("Password reset mail was sent");

    }
}
