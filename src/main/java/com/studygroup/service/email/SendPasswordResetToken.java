package com.studygroup.service.email;

import com.studygroup.entity.Member;
import com.studygroup.repository.UserRepository;
import com.studygroup.util.MailSender;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Qualifier("SendPasswordResetToken")
public class SendPasswordResetToken implements SendTokenToEmailService {


    private final MailSender mailSender;
    private final UserRepository userRepo;


    @Override
    public void sendTokenToEmail(Long memberId, String token) {

        Member member = Optional.of(
                            userRepo.findById(memberId)).
                            orElseThrow(BindParameterSupplier.
                                    bind(IllegalArgumentException::new,
                                            ErrorCode.NO_USER_ID.getMessage()));

        mailSender.sendTokenToEmail(
                token,
                member.getEmail(),
                "패스워드 리셋 이메일",
                "/api/auth/password-reset/token?=");


    }
}
