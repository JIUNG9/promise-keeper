package com.studygroup.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class GroupApplicationForm {

  @Size(min = 10, max = 100, message = "최소 길이 10자 최대 길이 100자입니다.")
  @NotNull
  String intro;
  @Size(min = 2, max = 10, message = "최소 길이 2자 최대 길이 10자입니다.")
  @NotNull
  String nickName;
}
