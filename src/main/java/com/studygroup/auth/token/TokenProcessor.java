package com.studygroup.auth.token;

import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenProcessor<T extends TokenData > {
  private final TokenValidator<T> tokenValidator;
  private final TokenHandler<T> tokenHandler;

  public boolean validate(T token){
  return tokenValidator.validate(token);
}
  public Cookie create(T token){
    return tokenHandler.create(token);
  }
  public void remove(T token){
    tokenHandler.remove(token);
  }
  public Cookie extend(T token){
    return tokenHandler.extend(token);
  }


}
