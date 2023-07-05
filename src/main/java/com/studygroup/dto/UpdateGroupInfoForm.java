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
>>>>>>> e1dee0ade05a324ee58dd2a5e10eb17391dfd2f2
}
