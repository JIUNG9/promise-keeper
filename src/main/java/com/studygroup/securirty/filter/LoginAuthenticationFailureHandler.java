package com.studygroup.securirty.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.studygroup.exception.ApiError;
import com.studygroup.util.constant.ConvertObjectToJson;
import com.studygroup.util.constant.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException e)
            throws IOException
    {

        ErrorCode errorCode = null;
        HttpStatus status = null;

        if(e instanceof UsernameNotFoundException){
            errorCode = ErrorCode.EMAIL_IS_NOT_EXISTED;
            status = HttpStatus.UNAUTHORIZED;
        }
        else if(e instanceof BadCredentialsException){
            errorCode = ErrorCode.LOGIN_FAIL;
            status = HttpStatus.UNAUTHORIZED;
        }
        else if(e instanceof DisabledException){
            errorCode = ErrorCode.EMAIL_NOT_VERIFIED;
            status = HttpStatus.FORBIDDEN;
            response.setStatus(HttpStatus.FORBIDDEN.value());
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
