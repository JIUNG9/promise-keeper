package com.studygroup.securirty.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studygroup.dto.MemberLoginForm;
import com.studygroup.entity.Member;
import com.studygroup.service.user.UserService;
import com.studygroup.util.CookieUtil;
import com.studygroup.util.JwtUtil;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@NoArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserService userDetailsService) {

        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/api/login");
        super.setAuthenticationManager(this.authenticationManager);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        log.info("attempt user login authentication");
        ObjectMapper om = new ObjectMapper();
        MemberLoginForm memberLoginForm = null;

        try {
            memberLoginForm = om.readValue(request.getInputStream(), MemberLoginForm.class);

        } catch (Exception e) {
            log.info("fail to read memberLoginForm");
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        memberLoginForm.getEmail(),
                        memberLoginForm.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        return authentication;
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                         Authentication authResult) throws IOException, ServletException {

        log.info("success the use authentication");
        SecurityContextHolder.getContext().setAuthentication(authResult);

        Long MemberId = ((Member)authResult.getPrincipal()).getId();
        String jwtToken = jwtUtil.generateToken(MemberId.toString());

        Cookie cookie = CookieUtil.create("jwtToken", jwtToken, 60*30, "localhost");
        response.addCookie(cookie);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {
        log.info("fail to user authentication");

        setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
        getFailureHandler().onAuthenticationFailure(request, response, failed);
    }
}