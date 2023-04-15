package com.studygroup.service.email;

import com.studygroup.entity.Member;
import com.studygroup.repository.EmailRepository;
import com.studygroup.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SendTokenToEmailServiceTest {

    @Autowired
    SendPasswordResetToken sendPasswordResetToken;
    @Autowired
    SendVerificationToken sendVerificationToken;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailRepository emailRepository;
    @Autowired
    SaveThePasswordResetToken saveThePasswordResetToken;
    @Autowired
    SaveTheVerificationToken saveTheVerificationToken;

    @BeforeEach
    void setUp() {
        Member member = Member.
                builder().
                email("rnwldnd7248@gmail.com").
                name("jiung").
                password("k1651227").
                age(20).
                build();
        userRepository.save(member);
    }

    @Test
    @Transactional
    void setSendVerificationToken() {

        Member member = userRepository.findByEmail("rnwldnd7248@gmail.com");
        String token = saveTheVerificationToken.save(member.getId());
        sendVerificationToken.sendTokenToEmail(member.getId(),token);
    }

    @Test
    @Transactional
    void setSendPasswordResetToken() {
        Member member = userRepository.findByEmail("rnwldnd7248@gmail.com");
        String token = saveThePasswordResetToken.save(member.getId());
        sendPasswordResetToken.sendTokenToEmail(member.getId(),token);

    }
}