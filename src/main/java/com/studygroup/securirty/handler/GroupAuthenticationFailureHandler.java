package com.studygroup.securirty.handler;

import com.studygroup.exception.ApiError;
import com.studygroup.exception.GroupAdminAuthorizationException;
import com.studygroup.exception.GroupMemberAuthorizationException;
import com.studygroup.util.constant.ConvertObjectToJson;
import com.studygroup.util.constant.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class GroupAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        HttpStatus status = null;
        ErrorCode errorCode = null;

        if (exception instanceof GroupAdminAuthorizationException) {
            errorCode = ErrorCode.YOU_ARE_NOT_GROUP_ADMIN;
            status = HttpStatus.UNAUTHORIZED;

        } else if(exception instanceof GroupMemberAuthorizationException) {
            errorCode = ErrorCode.YOU_ARE_NOTE_GROUP_MEMBER;
            status = HttpStatus.UNAUTHORIZED;
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
