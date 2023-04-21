package com.studygroup.service.user;

import com.studygroup.entity.Member;
import com.studygroup.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NormalSignUpServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    SignUpService signUpService;




    @Test
    @Transactional
    void SignUP_SignUpFail_WhenThereIsDuplicatedEmail()
    {
        Member member = Member.
                builder().
                email("rnwldnd7248@gmail.com").
                name("jiung").
                password("k1651227").
                age(20).
                build();
        userRepository.save(member);
        signUpService.signUp(member);
    }
    @Test
    @Transactional
    void SignUp_SignUPSuccess_whenThereIsNotDuplicatedEmail() {

        Member member = Member.
                builder().
                email("rnwldnd7248@gmail.com").
                name("jiung").
                password("k1651227").
                age(20).
                build();

        signUpService.signUp(member);

        assertEquals(
                userRepository.findByEmail("rnwldnd7248@gmail.com").getEmail(),
                member.getEmail());
    }
}