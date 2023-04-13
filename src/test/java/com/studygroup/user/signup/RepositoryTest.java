package com.studygroup.user.signup;

import com.studygroup.controller.UserController;
import com.studygroup.entity.EmailToken;
import com.studygroup.entity.Member;
import com.studygroup.repository.UserRepository;
import com.studygroup.service.UserService;
import com.studygroup.service.UserServiceImpl;
import com.studygroup.util.constant.TokenGenerator;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepo;

    @BeforeEach
    public void init(){
        userService = new UserServiceImpl(userRepo,new BCryptPasswordEncoder());
    }

    @Test
    @Transactional
    public void testMemberSignUp(){

        EmailToken token = EmailToken.
                                builder().
                                confirmationToken(TokenGenerator.setTokenString()).
                                build();

        List<EmailToken> tokenList = new ArrayList<>();
        tokenList.add(token);

        Member member = Member
                                .builder().
                                email("rnwldnd7248@gmail.com").
                                name("지웅").
                                password("!Rnwl1652").
                                emailTokenList(tokenList).
                                build();

        userService.signUp(member);
        //member
        assertEquals()
        //token
        assertEquals()
    }

    @Test
    @Transactional
    public void userStatusIsDisabled(){

        Member member = Member
                .builder().
                email("rnwldnd7248@gmail.com").
                name("지웅").
                password("!Rnwl1652").
                build();

        userService.signUp(member);
        assertEquals(member.isEnabled(), userRepo.findByEmail("rnwldnd7248@gmail.com").isEnabled());
    }
}
