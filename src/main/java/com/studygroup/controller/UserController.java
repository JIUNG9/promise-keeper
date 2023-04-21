package com.studygroup.controller;

import com.studygroup.dto.LoginForm;
import com.studygroup.dto.MemberLoginForm;
import com.studygroup.dto.MemberSignUpForm;

import com.studygroup.entity.Member;
import com.studygroup.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.studygroup.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);

    @PostMapping("/api/sign-up")
    public ResponseEntity<Object> userSave(@RequestBody @Valid MemberSignUpForm memberForm) {

        logger.info("바인딩 에러, 중복된 이메일 모두 해당되지 않습니다.");

        Member member = Member.
                builder().
                email(memberForm.getEmail()).
                name(memberForm.getName()).
                password(memberForm.getPassword()).
                gender(memberForm.getGender()).
                age(memberForm.getAge()).
                build();

        userService.signUp(member);
        logger.info("회원가입이 완료 되었습니다");

        return ResponseEntity.status(HttpStatus.CREATED).body("User is successfully saved but need to verify the email");

    }



    @PostMapping("/api/login")
    public ResponseEntity<Object> userNormalLogin(@RequestBody MemberLoginForm loginForm) {

        return ResponseEntity.status(HttpStatus.OK).body("login is succeeded");
    }


//    @PostMapping("/api/user/sign-up")
//    @PostMapping("/api/user/sign-up")
//    @PostMapping("/api/user/sign-up")
//    @PostMapping("/api/user/sign-up")
}
