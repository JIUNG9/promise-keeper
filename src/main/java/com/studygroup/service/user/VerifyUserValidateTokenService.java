package com.studygroup.service.user;

import com.studygroup.entity.Member;
import com.studygroup.enums.TokenType;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.ChatRoomMemberRepository;
import com.studygroup.repository.UserRepository;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service("VerifyUserValidateTokenService")
public class VerifyUserValidateTokenService implements VerifyTheTokenService {

    private final UserRepository userRepository;


    @Override
    public void verifyTheToken(String token) {

       Member member = Optional.
               ofNullable(userRepository.
                       findMemberByEmailTokenListByConfirmationToken(token,
                               TokenType.VERIFICATION_TOKEN))
               .orElseThrow(BindParameterSupplier.
                       bind(CustomIllegalArgumentException::new,
                               ErrorCode.TOKEN_IS_NOT_VALID));
    }
}
