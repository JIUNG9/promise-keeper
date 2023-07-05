package com.studygroup.service.user;

import com.studygroup.domain.Member;
import com.studygroup.enums.TokenType;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.UserRepository;
import com.studygroup.util.error.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("RetrieveMemberByVerificationTokenService")
public class RetrieveMemberByVerificationTokenService implements RetrieveMemberByToken {

  private final UserRepository userRepository;

  @Override
  public Member getMember(String token) {

    return Optional.
        ofNullable(userRepository.
            findMemberByEmailTokenListByConfirmationToken(
                token,
                TokenType.VERIFICATION_TOKEN)).
        orElseThrow(
            BindParameterSupplier.
                bind(CustomIllegalArgumentException::new,
                    ErrorCode.TOKEN_IS_NOT_VALID));
  }
}
