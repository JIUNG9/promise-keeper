package com.studygroup.service.user;

import com.studygroup.domain.EmailToken;
import com.studygroup.enums.TokenType;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.UserRepository;
import com.studygroup.util.error.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VerifyPasswordResetTokenStrategy implements VerifyUserEmailTokenStrategy<EmailToken> {

  private final UserRepository userRepository;

  @Override
  public boolean verifyTheToken(EmailToken token) {

    Optional.
        ofNullable(userRepository.
            findMemberByEmailTokenListByConfirmationToken(
                token.getValue(),
                TokenType.PASSWORD_RESET_TOKEN)).
        orElseThrow(BindParameterSupplier.
            bind(CustomIllegalArgumentException::new,
                ErrorCode.TOKEN_IS_NOT_VALID));

  }
}
