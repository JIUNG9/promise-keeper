package com.studygroup.service.email;

import com.studygroup.entity.Member;
import com.studygroup.repository.ChatRoomMemberRepository;
import com.studygroup.repository.UserRepository;
import com.studygroup.util.MailSender;
import com.studygroup.util.constant.EmailSentURI;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
