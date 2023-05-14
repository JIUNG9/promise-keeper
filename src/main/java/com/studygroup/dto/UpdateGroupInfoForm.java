package com.studygroup.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Getter
public class UpdateGroupInfoForm {
    @Size(min = 10, max = 200, message = "그룹 소개 길이를 최소 10자 최대 200자로 작성해주세요")
    private String intro;
}
