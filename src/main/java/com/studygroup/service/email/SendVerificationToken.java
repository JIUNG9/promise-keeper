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
