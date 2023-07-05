package com.studygroup.securirty.handler;

import com.studygroup.exception.ApiError;
import com.studygroup.util.convertor.ConvertObjectToJson;
import com.studygroup.util.error.ErrorCode;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class LoginAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException exception) throws IOException, ServletException {

    ErrorCode errorCode = null;

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
    for (GrantedAuthority authority : authorities) {
      if (authority.getAuthority().equals("ROLE_USER")) {
        errorCode = ErrorCode.YOU_ARE_NOT_ADMIN;
        break;
      } else if (authority.getAuthority().equals("ROLE_KICKED")) {
        errorCode = ErrorCode.YOU_ARE_KICKED;
        break;
      }
    }
    ResponseEntity responseEntity = ApiError.buildApiError(
        ApiError.builder().
            timestamp(LocalDateTime.now()).
            status(HttpStatus.UNAUTHORIZED).
            message(errorCode.getMessage()).
            code(errorCode.getCode()).
            build());

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.getWriter().write(ConvertObjectToJson.convert(responseEntity.getBody()));
  }
}
