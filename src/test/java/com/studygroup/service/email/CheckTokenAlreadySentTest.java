package com.studygroup.service.email;

import com.studygroup.entity.EmailToken;
import com.studygroup.entity.Member;
import com.studygroup.enums.TokenType;
import com.studygroup.repository.EmailRepository;
import com.studygroup.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CheckTokenAlreadySentTest {

    @Autowired
    CheckVerificationTokenAlreadySent checkVerificationTokeAlreadySent;
    @Autowired
    CheckResetPasswordTokenAlreadySent checkResetPasswordTokenAlreadySent;
    @Autowired
    UserRepository userRepo;
    @Autowired
    EmailRepository emailRepository;
    @Autowired
    SaveTheVerificationToken saveTheVerificationToken;
    @Autowired
    SaveThePasswordResetToken saveThePasswordResetToken;

    @BeforeEach
    void setUp() {
        Member member = Member.
                builder().
                email("rnwldnd7248@gmail.com").
                name("jiung").
                password("k1651227").
                age(20).
                build();
        userRepo.save(member);

        saveThePasswordResetToken.save(member.getId());
        saveThePasswordResetToken.save(member.getId());
        saveThePasswordResetToken.save(member.getId());

        saveTheVerificationToken.save(member.getId());
        saveTheVerificationToken.save(member.getId());
        saveTheVerificationToken.save(member.getId());

    }

    @Test
    @Transactional
    void setCheckVerificationTokenSen(){

        Member member = userRepo.findByEmail("rnwldnd7248@gmail.com");

        checkVerificationTokeAlreadySent.checkTokenSentIfSoDelete(member.getId());
        List<EmailToken> emailTokenList = emailRepository.findByMember_Id(member.getId()).
                                            stream().
                                            filter(s->s.getTokenType().equals(TokenType.VERIFICATION_TOKEN)).
                                            collect(Collectors.toList());

        assertEquals(emailTokenList.size(), 0);

    }

    @Test
    @Transactional
    void setCheckResetPasswordTokenSent() {

        Member member = userRepo.findByEmail("rnwldnd7248@gmail.com");

        checkResetPasswordTokenAlreadySent.checkTokenSentIfSoDelete(member.getId());
        List<EmailToken> emailTokenList = emailRepository.findByMember_Id(member.getId()).
                                            stream().
                                            filter(s -> s.getTokenType().equals(TokenType.PASSWORD_RESET_TOKEN))
                                            .collect(Collectors.toList());

        assertEquals(emailTokenList.size(), 0);
    }
}