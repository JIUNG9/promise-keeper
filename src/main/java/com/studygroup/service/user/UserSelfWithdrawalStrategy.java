package com.studygroup.service.user;

import com.studygroup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSelfWithdrawalStrategy implements DeleteUserStrategy<Long> {

  private final UserRepository userRepository;

  @Override
  public void delete(Long memberId) {
      userRepository.deleteById(memberId);

  }
}
