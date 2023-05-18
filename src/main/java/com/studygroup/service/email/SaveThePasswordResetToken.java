package com.studygroup.service.email;

import com.studygroup.entity.EmailToken;
import com.studygroup.entity.Member;
import com.studygroup.enums.TokenType;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.EmailRepository;
import com.studygroup.repository.ChatRoomMemberRepository;
import com.studygroup.repository.UserRepository;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.constant.TokenGenerator;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("SaveThePasswordResetToken")
@RequiredArgsConstructor
public class SaveThePasswordResetToken implements SaveTheToken {

    private final EmailRepository emailRepo;


    @Override
    public String save(Member member) {

        String token = TokenGenerator.setTokenString();


        EmailToken emailToken = EmailToken.
                builder().
                member(member).
                tokenType(TokenType.PASSWORD_RESET_TOKEN).
                confirmationToken(token).
                build();

                emailRepo.save(emailToken);

        return token;

    }
}
