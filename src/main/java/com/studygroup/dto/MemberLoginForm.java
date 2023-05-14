package com.studygroup.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
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
