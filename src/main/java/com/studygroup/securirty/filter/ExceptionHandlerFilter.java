package com.studygroup.securirty.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.studygroup.exception.ApiError;
import com.studygroup.util.constant.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String message = null;
        ErrorCode errorCode = null;

        try {
            filterChain.doFilter(request, response);
        }
        catch (AuthenticationException e) {
            message  = e.getMessage();
            errorCode = ErrorCode.AUTHORIZATION_ERROR;
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
        catch (Exception e){
            message = e.getMessage();
            errorCode = ErrorCode.AUTHORIZATION_ERROR;
        }

        ResponseEntity responseEntity = ApiError.buildApiError(
                ApiError.builder().
                        timestamp(LocalDateTime.now()).
                        status(HttpStatus.UNAUTHORIZED).
                        message(message).
                        build());

        response.getWriter().write(convertObjectToJson(responseEntity.getBody()));
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {

        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }
}