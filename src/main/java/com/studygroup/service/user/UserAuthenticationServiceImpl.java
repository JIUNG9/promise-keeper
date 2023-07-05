package com.studygroup.service.user;

import com.studygroup.domain.Member;
import com.studygroup.repository.UserRepository;
import com.studygroup.util.error.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public Member loadUserByUsername(String email) {
    return Optional.
        ofNullable(userRepository.findByEmail(email)).
        orElseThrow(BindParameterSupplier.bind(
            UsernameNotFoundException::new, ErrorCode.EMAIL_IS_NOT_EXISTED.getMessage()));
  }
}
