package com.studygroup.securirty.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studygroup.service.user.UserService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@NoArgsConstructor
public class JwtAuthorizationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private UserService userDetailsService;

    public JwtAuthorizationFilter(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserService userDetailsService) {

        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        setFilterProcessesUrl("/api/login");
        super.setAuthenticationManager(this.authenticationManager);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        log.info("User authentication filter");
        ObjectMapper om = new ObjectMapper();
        MemberLoginForm memberLoginForm = null;

        try {
            memberLoginForm = om.readValue(request.getInputStream(), MemberLoginForm.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("member : {}", memberLoginForm);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberLoginForm.getEmail(), memberLoginForm.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        return authentication;
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                         Authentication authResult) throws IOException, ServletException {

        logger.info("the email from request: " + request.getAttribute("email"));
        String email = (String) request.getAttribute("email");

        String jwtToken = jwtUtil.generateToken(email);
        logger.info("token is generated, and login succeeded");


        response.addHeader("id", String.valueOf(userDetailsService.(email)));


    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {
        setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
        getFailureHandler().onAuthenticationFailure(request, response, failed);
    }
}