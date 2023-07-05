package com.studygroup.dto;

import com.studygroup.validation.Password;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PasswordUpdateForm {

  @Password
  String password;
}
