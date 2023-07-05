package com.studygroup.dto.member;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@AllArgsConstructor
@Builder

public class MemberLoginForm {

  @NotNull
  private String email;
  @NotNull
  private String password;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("LoginForm")
        .append("{")
        .append(", email='").append(email).append('\'')
        .append(", password='").append(password).append('\'')
        .append('}');
    return sb.toString();
  }
}
