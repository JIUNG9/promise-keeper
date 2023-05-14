package com.studygroup.dto;

import com.studygroup.validation.Password;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@RequiredArgsConstructor
public class PasswordUpdateForm {

    @Password
    String password;
}
