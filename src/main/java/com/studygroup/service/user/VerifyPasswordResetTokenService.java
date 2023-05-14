package com.studygroup.service.user;

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
@Service("VerifyPasswordResetTokenService")
public class VerifyPasswordResetTokenService implements VerifyTheTokenService {

    private final UserRepository userRepository;

    @Override
    public void verifyTheToken(String token) {

        Optional.
                ofNullable(userRepository.
                        findMemberByEmailTokenListByConfirmationToken(
                                token,
                                TokenType.PASSWORD_RESET_TOKEN)).
                orElseThrow(BindParameterSupplier.
                        bind(CustomIllegalArgumentException::new,
                                ErrorCode.TOKEN_IS_NOT_VALID));

    }
}
