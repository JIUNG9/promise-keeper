package com.studygroup.auth.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class TokenValidator<T extends TokenData> {
  private final TokenValidationStrategy<T> verifyTokenStrategy;
  public  boolean validate(T token){
    return verifyTokenStrategy.performTokenOperation(token);
  }

}
