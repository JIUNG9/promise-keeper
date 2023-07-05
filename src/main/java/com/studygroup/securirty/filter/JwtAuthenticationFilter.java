package com.studygroup.securirty.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studygroup.auth.token.JwtToken;
import com.studygroup.auth.token.TokenProcessor;
import com.studygroup.domain.Member;
import com.studygroup.dto.member.MemberLoginForm;
import com.studygroup.securirty.handler.LoginAuthenticationFailureHandler;
import com.studygroup.util.token.JwtUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

@Slf4j
@NoArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private  AuthenticationManager authenticationManager;
  private TokenProcessor<JwtToken<SimpleGrantedAuthority>> tokenProcessor;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager, TokenProcessor<JwtToken<SimpleGrantedAuthority>> tokenProcessor) {
    this.authenticationManager = authenticationManager;
    this.tokenProcessor = tokenProcessor;
    setFilterProcessesUrl("/api/login");
    super.setAuthenticationManager(this.authenticationManager);
  }





  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response)
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
  public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain,
      Authentication authResult) throws IOException, ServletException {

    log.info("success the use authentication");
    SecurityContextHolder.getContext().setAuthentication(authResult);

    Long memberId = ((Member) authResult.getPrincipal()).getId();
    List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) ((Member) authResult.getPrincipal()).getAuthorities();
    String tokenValue = JwtUtil.generateToken(memberId.toString(), authorities);
    Cookie cookie = tokenProcessor.create(new JwtToken<SimpleGrantedAuthority>("jwtToken", tokenValue, authorities));

    response.addCookie(cookie);

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.getWriter().write(new ObjectMapper().writeValueAsString(cookie.getValue()));

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