package com.studygroup.dto;

import com.studygroup.validation.Password;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class MemberSignUpForm {

    @Email(message = "이메일 형식에 맞춰서 입력을 다시 해주세요.")
    @NotNull
    private String email;
    @NotNull
    @Length(min = 2, max = 10, message = "2글자 이상 11글자 미만이여야합니다")
    private String name;
    @NotNull
    @Password
    private String password;
}
