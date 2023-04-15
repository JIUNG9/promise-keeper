package com.studygroup.service.email;

import com.studygroup.entity.EmailToken;
import com.studygroup.entity.Member;
import com.studygroup.enums.TokenType;
import com.studygroup.repository.EmailRepository;
import com.studygroup.repository.UserRepository;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.constant.TokenGenerator;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Qualifier("SaveTheVerificationToken")
public class SaveTheVerificationToken implements SaveTheToken {

    private final EmailRepository emailRepo;
    private final UserRepository userRepo;
    @Override
    public String save(Long memberId) {

        String token = TokenGenerator.setTokenString();
        Member member = userRepo.findById(memberId);

        EmailToken emailToken = EmailToken.
                builder().
                member(member).
                tokenType(TokenType.VERIFICATION_TOKEN).
                confirmationToken(token).
                build();

            Optional.of(
                        emailRepo.save(emailToken)).
                            orElseThrow(BindParameterSupplier.
                                bind(IllegalArgumentException::new,
                                    ErrorCode.SAVE_ERROR.getMessage()));

            return token;
    }
}
