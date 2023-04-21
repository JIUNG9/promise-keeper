package com.studygroup.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberLoginForm {
    private String email;
    private String password;
}
