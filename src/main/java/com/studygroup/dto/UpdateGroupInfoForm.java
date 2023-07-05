package com.studygroup.dto;

<<<<<<< HEAD
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UpdateGroupInfoForm {

  @Size(min = 10, max = 200, message = "그룹 소개 길이를 최소 10자 최대 200자로 작성해주세요")
  private String intro;
=======
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Getter
public class UpdateGroupInfoForm {
    @Size(min = 10, max = 200, message = "그룹 소개 길이를 최소 10자 최대 200자로 작성해주세요")
    private String intro;
>>>>>>> 7c3dbf05b4ae7da5f5b0cb57a6434bd46693aa91
}
