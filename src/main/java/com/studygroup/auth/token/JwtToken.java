package com.studygroup.auth.token;

import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class JwtToken<E extends GrantedAuthority> implements TokenData {

  public JwtToken(String value){
    this.value = value;
  }
  private String subject;
  private String value;
  private List<E> authorityList;
  @Override
  public String getValue() {
    return this.value;
  }
}
