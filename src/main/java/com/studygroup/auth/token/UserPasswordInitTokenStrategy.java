package com.studygroup.auth.token;

import com.studygroup.repository.EmailRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserPasswordInitTokenStrategy implements UserVerificationByTokenTypeStrategy<String> {
  private final EmailRepository emailRepository;
  @Override
  public boolean performTokenOperation(String token) {
    return emailRepository.findByValue(token).isPresent();
  }
}
