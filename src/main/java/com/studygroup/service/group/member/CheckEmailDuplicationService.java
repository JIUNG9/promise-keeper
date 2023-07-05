package com.studygroup.service.group.member;

import com.studygroup.repository.UserRepository;
import com.studygroup.service.common.DuplicationCheckerProvider;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckEmailDuplicationService implements DuplicationCheckerProvider<String> {
  private final UserRepository userRepository;
  @Override
  public boolean isDuplicated(String email) {
    return Optional.ofNullable(userRepository.findByEmail(email)).isPresent();
  }
}
