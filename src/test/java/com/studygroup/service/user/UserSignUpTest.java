package com.studygroup.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserSignUpTest {

    @Autowired

    @BeforeEach
    void setUp() {
    }

    @Test
    void signUp_IfEmailDuplicated_FailMessage(){

    }

    @Test
    void signUp_IfNotQualifiedEmailRegex_FailMessage() {

    }

    @Test
    void signUp_IfNotQualifiedPasswordRegex_FailMessage() {

    }

    @Test
    void signUp_IfNotQualifiedNameLength_FailMessage() {

    }


    @Test
    void signUp() {
    }
}