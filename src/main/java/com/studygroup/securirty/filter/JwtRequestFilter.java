package com.studygroup.securirty.filter;


import com.studygroup.exception.ApiError;
import com.studygroup.util.convertor.ConvertObjectToJson;
import com.studygroup.util.error.ErrorCode;
import com.studygroup.util.cookie.CookieUtil;
import com.studygroup.util.token.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


@RequiredArgsConstructor
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException, AuthenticationException {

    String token = CookieUtil.getValue(request, "jwtToken");

    HttpStatus status = null;
    ErrorCode errorCode = null;

    try {
      if (token != null && JwtUtil.validateToken(token) && !JwtUtil.checkTokenIsExpired(token)) {
        Authentication authentication = JwtUtil.getAuthenticationFromToken(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      } else {
        status = HttpStatus.UNAUTHORIZED;
        errorCode = ErrorCode.JWT_TOKEN_NOT_VALID;
        response.
            getWriter().
            write(ConvertObjectToJson.convert(
                ApiError.
                    buildApiError(errorCode, status).getBody()));
        response.setStatus(401);
        return;
      }
    } catch (ExpiredJwtException e) {
      status = HttpStatus.UNAUTHORIZED;
      errorCode = ErrorCode.JWT_TOKEN_EXPIRED;
      response.
          getWriter().
          write(ConvertObjectToJson.convert(
              ApiError.
                  buildApiError(errorCode, status).getBody()));
      response.setStatus(401);
      return;
    }

    filterChain.doFilter(request, response);


  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getRequestURI();
    return path.equals("/api/users") || path.equals("/api/login") || path.contains("/api/auth/");
  }
}

