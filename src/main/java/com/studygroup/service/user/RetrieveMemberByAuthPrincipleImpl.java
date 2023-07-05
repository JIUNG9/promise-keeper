package com.studygroup.service.user;

import com.studygroup.domain.Member;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.UserRepository;
import com.studygroup.util.error.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("RetrieveMemberByIdService")
@RequiredArgsConstructor
public class RetrieveMemberByAuthPrincipleImpl implements RetrieveMemberByAuthPrinciple {

  private final UserRepository userRepository;

  @Override
  public Member getMember(Long id) {

    return userRepository.
        findById(id).
        orElseThrow(BindParameterSupplier.
            bind(CustomIllegalArgumentException::new, ErrorCode.NO_USER_ID));

  }
}
