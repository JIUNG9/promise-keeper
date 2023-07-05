package com.studygroup.securirty.handler;

import com.studygroup.exception.ApiError;
import com.studygroup.exception.GroupAdminAuthorizationException;
import com.studygroup.exception.GroupMemberAuthorizationException;
import com.studygroup.exception.GroupMemberIsDeniedException;
import com.studygroup.exception.GroupMemberIsPendingException;
import com.studygroup.util.convertor.ConvertObjectToJson;
import com.studygroup.util.error.ErrorCode;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class GroupAuthenticationFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException {

    HttpStatus status = null;
    ErrorCode errorCode = null;

    if (exception instanceof GroupAdminAuthorizationException) {
      errorCode = ErrorCode.YOU_ARE_NOT_GROUP_ADMIN;
      status = HttpStatus.UNAUTHORIZED;

    } else if (exception instanceof GroupMemberAuthorizationException) {
      errorCode = ErrorCode.YOU_ARE_NOT_GROUP_MEMBER;
      status = HttpStatus.UNAUTHORIZED;

    } else if (exception instanceof GroupMemberIsPendingException) {
      errorCode = ErrorCode.YOU_ARE_NOW_PENDING_STATUS;
      status = HttpStatus.FORBIDDEN;

    } else if (exception instanceof GroupMemberIsDeniedException) {
      errorCode = ErrorCode.YOU_ARE_DENIED_FROM_GROUP_ADMIN;
      status = HttpStatus.FORBIDDEN;
    }

    ResponseEntity responseEntity = ApiError.buildApiError(
        ApiError.builder().
            timestamp(LocalDateTime.now()).
            status(status).
            message(errorCode.getMessage()).
            code(errorCode.getCode()).
            build());

    response.getWriter().write(ConvertObjectToJson.convert(responseEntity.getBody()));

  }
}
