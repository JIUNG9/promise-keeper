package com.studygroup.securirty.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studygroup.dto.MemberLoginForm;
import com.studygroup.entity.Member;
import com.studygroup.securirty.handler.LoginAuthenticationFailureHandler;
import com.studygroup.util.CookieUtil;
import com.studygroup.util.JwtUtil;
import com.studygroup.util.constant.LoginExpirationTime;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@NoArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {

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
        Authentication authentication = null;


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


             authentication = authenticationManager.authenticate(authenticationToken);

        return authentication;
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                         Authentication authResult) throws IOException, ServletException {

        log.info("success the use authentication");
        SecurityContextHolder.getContext().setAuthentication(authResult);

        Long MemberId = ((Member)authResult.getPrincipal()).getId();
        List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) ((Member) authResult.getPrincipal()).getAuthorities();
        String jwtToken = JwtUtil.generateToken(MemberId.toString(), authorities);

        Cookie cookie = CookieUtil.create("jwtToken", jwtToken, LoginExpirationTime.LOGIN_EXPIRATION_TIME, "localhost");
        response.addCookie(cookie);

        // Write the response to the client
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writeValueAsString("login is successful"));

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {
        log.info("fail to user authentication");
        setAuthenticationFailureHandler(new LoginAuthenticationFailureHandler());
        getFailureHandler().onAuthenticationFailure(request, response, failed);
    }
}