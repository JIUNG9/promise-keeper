package com.studygroup.auth.token;

import com.studygroup.repository.EmailRepository;
import com.studygroup.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserEmailAuthorizationTokenStrategy implements UserVerificationByTokenTypeStrategy<String> {

  private final EmailRepository emailRepository;
  @Override
  public boolean performTokenOperation(String token) {
    return emailRepository.findByValue(token).isPresent();
  }
}
