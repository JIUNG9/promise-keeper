package com.studygroup.service.user;

import com.studygroup.dto.MemberSignUpForm;
import com.studygroup.validation.PasswordValidator;
import io.netty.handler.codec.DefaultHeaders;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.LengthValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


import javax.validation.*;
import javax.validation.constraints.Email;
import java.util.Set;

@SpringBootTest
public class UserSignUpValidationCheckTest {

    @Autowired
    PasswordValidator passwordValidator;
    Validator validator = null;

    public UserSignUpValidationCheckTest(){
    }

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Test
    void signUp_IfNotQualifiedEmailRegex_FailMessage() {
        MemberSignUpForm memberSignUpForm = new MemberSignUpForm("rnwldnd7248", "jiung", "!Rnwl1652");
        Set<ConstraintViolation<MemberSignUpForm>> violations = validator.validate(memberSignUpForm);
        String message = violations.stream().map(m->m.getMessage()).findFirst().get();
        assertEquals("이메일 형식에 맞춰서 입력을 다시 해주세요.", message);

    }

    @Test
    void signUp_IfQualifiedEmailRegex_NoErrorMessage() {
        MemberSignUpForm memberSignUpForm = new MemberSignUpForm("rnwldnd7248@gmail.com", "jiung", "!Rnwl1652");
        Set<ConstraintViolation<MemberSignUpForm>> violations = validator.validate(memberSignUpForm);
        assertEquals(violations.size(),0);

    }
    @Test
    void signUp_IfNotQualifiedPasswordRegex_FailMessage() {
        MemberSignUpForm memberSignUpForm = new MemberSignUpForm("rnwldnd7248@gmail.com", "jiung", "1234");
        Set<ConstraintViolation<MemberSignUpForm>> violations = validator.validate(memberSignUpForm);
        String message = violations.stream().map(m -> m.getMessage()).findFirst().get();
        assertEquals("8자 이상의 50자 이하의 숫자, 영문자, 특수문자를 포함한 비밀번호를 입력해주세요", message);
    }

    @Test
    void signUp_IfQualifiedPasswordRegex_NoErrorMessage() {
        MemberSignUpForm memberSignUpForm = new MemberSignUpForm("rnwldnd7248@gmail.com", "jiung", "!Rnwl1652");
        Set<ConstraintViolation<MemberSignUpForm>> violations = validator.validate(memberSignUpForm);
        assertEquals(violations.size(), 0);
    }

    @Test
    void signUp_IfNotQualifiedNameLength_FailMessage() {
        MemberSignUpForm memberSignUpForm = new MemberSignUpForm("rnwldnd7248@gmail.com", "j", "!Rnwl1652");
        Set<ConstraintViolation<MemberSignUpForm>> violations = validator.validate(memberSignUpForm);
        String message = violations.stream().map(m -> m.getMessage()).findFirst().get();
        assertEquals("2글자 이상 11글자 미만이여야합니다", message);    }

    @Test
    void signUp_IfNotQualifiedNameLength_NoErrorMessage() {
        MemberSignUpForm memberSignUpForm = new MemberSignUpForm("rnwldnd7248@gmail.com", "ji", "!Rnwl1652");
        Set<ConstraintViolation<MemberSignUpForm>> violations = validator.validate(memberSignUpForm);
        assertEquals(violations.size(), 0);

    }
}
