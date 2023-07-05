package com.studygroup.dto.member;

import com.studygroup.enums.Gender;
import com.studygroup.validation.Password;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class MemberSignUpForm {

  @Email(message = "이메일 형식에 맞춰서 입력을 다시 해주세요.")
  @NotNull
  private String email;

  @NotNull
  @Password
  private String password;

  @NotNull
  @Length(min = 2, max = 10, message = "2글자 이상 11글자 미만이여야합니다")
  private String name;

  @NotNull
  private int age;

  @NotNull
  private Gender gender;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("MemberSignUpForm")
        .append("{")
        .append(", email='").append(email).append('\'')
        .append(", password='").append(password).append('\'')
        .append(", name='").append(name).append('\'')
        .append('}');
    return sb.toString();
  }


}
