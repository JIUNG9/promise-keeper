package com.studygroup.auth.token;

import com.studygroup.util.token.JwtUtil;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@NoArgsConstructor
@RequiredArgsConstructor
public class JwtTokenValidationStrategy implements TokenValidationStrategy<JwtToken<SimpleGrantedAuthority>> {
  @Override
  public boolean performTokenOperation(JwtToken<SimpleGrantedAuthority> token) {
    return JwtUtil.validateToken(token.getValue());
  }
}
