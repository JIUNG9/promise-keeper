package com.studygroup.auth.token;

import com.studygroup.util.cookie.CookieBuilder;
import com.studygroup.util.cookie.CookieUtil;
import com.studygroup.util.token.TokenBlacklist;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NormalLoginJwtTokenHandler implements TokenHandler<JwtToken<SimpleGrantedAuthority>> {


  @Override
  public Cookie create(JwtToken<SimpleGrantedAuthority> token) {
    return CookieUtil.defaultJwtTokenCookie(token.getValue());
  }

  @Override
  public void remove(JwtToken<SimpleGrantedAuthority> token) {
    TokenBlacklist.addTokenToBlacklist(token.getValue());
  }

  @Override
  public Cookie extend(JwtToken<SimpleGrantedAuthority> token) {
    this.remove(token);
    return CookieBuilder.defaultBuilder("jwtToken", token.getValue()).defaultConstructorWithOtherDefault()
        .build();
  }
}
