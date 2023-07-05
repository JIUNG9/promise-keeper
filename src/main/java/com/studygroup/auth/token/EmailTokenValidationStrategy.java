package com.studygroup.auth.token;

import com.studygroup.domain.EmailToken;
import com.studygroup.enums.TokenType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailTokenValidationStrategy implements TokenValidationStrategy<String> {
    private final UserVerificationByTokenTypeStrategy<String> userVerificationByTokenTypeStrategy;
    @Override
    public boolean performTokenOperation(String token) {
      return userVerificationByTokenTypeStrategy.performTokenOperation(token);
    }
}
