package com.studygroup.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studygroup.dto.LoginForm;
import com.studygroup.entity.Member;
import com.studygroup.repository.UserRepository;
import com.studygroup.securirty.filter.JwtAuthenticationFilter;
import com.studygroup.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NormalLoginTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtTokenUtil;

    @Mock
    private UserService userDetailsService;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private FilterChain filterChain;

    @BeforeEach
    void setUp() throws IOException, ServletException {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        filterChain = new MockFilterChain();

        Member member = Member.
                builder().
                email("rnwldnd7248@gmail.com").
                name("jiung").
                password("k1651227").
                age(20).
                build();
        userRepository.save(member);

        // Arrange


        // Act
        jwtAuthenticationFilter.doFilter(request, response, filterChain);
    }

    @Test
    void Login_LoginSuccess_FormIsMatchedInformationFromDB() throws Exception {
        /*
        1. SecurityContextHolder에서 authentication을 얻어와서 일치하는지 확인
        2. Cookie의 JWT Token과 일치하는지 확인
        3. filter가 정상동작하는지 확인
         */

        String email = "rnwldnd7248@gmail.com";
        String password = "!Rnwl652";
        String jwtToken = "jwtToken";

        LoginForm loginForm = new LoginForm(email, password);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        when(authenticationManager.authenticate(authentication)).thenReturn(authentication);

        Member userDetails = Member.builder().email(email).password(password).build();
        when(userDetailsService.loadUserByUsername(email)).thenReturn(userDetails);

        when(jwtTokenUtil.generateToken(userDetails.getId().toString())).thenReturn(jwtToken);

        request.setMethod("POST");
        request.setRequestURI("/api/login");
        request.setContentType(MediaType.APPLICATION_JSON_VALUE);
        request.setContent(new ObjectMapper().writeValueAsString(loginForm).getBytes());

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isEqualTo(authentication);
        assertThat(response.getHeader("Set-Cookie")).isEqualTo("jwtToken=" + jwtToken + "; Path=/; HttpOnly");
        verify(filterChain).doFilter(request, response);
    }


    //1. 사용자 정보 비밀번호 일치하지 않을 때

    //2. 사용자 정보를 입력하지 않을 때

    //3. 사용자 정보가 일치할 때
}
