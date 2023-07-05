package com.studygroup.service.user;

import com.studygroup.domain.Member;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.UserRepository;
import com.studygroup.util.error.ErrorCode;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("RetrieveMemberByEmailService")
@RequiredArgsConstructor
public class RetrieveMemberByEmailServiceImpl implements RetrieveMemberByEmail {

  private final UserRepository userRepository;

  @Override
  public Member getMember(String email) {

    return Optional.
        ofNullable(userRepository.findByEmail(email)).
        orElseThrow(() -> new CustomIllegalArgumentException(ErrorCode.EMAIL_IS_NOT_EXISTED));

  }
}
