package com.studygroup.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandler {

    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> RuntimeException(Exception ex) {
        return ApiError.buildApiError(
                        ApiError.
                        builder().
                        message(ex.getMessage()).
                        timestamp(LocalDateTime.now()).
                        status(HttpStatus.BAD_REQUEST).
                        build());
    }


    @Order(Ordered.HIGHEST_PRECEDENCE)
    class UserValidationException {

        @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
        private ResponseEntity<Object> ValidationError(MethodArgumentNotValidException ex) {

            List<ApiSubError> subErrors = ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(x -> ApiValidationError.builder().message(x.getDefaultMessage()).rejectedValue(x.getRejectedValue()).field(x.getField()).object(x.getObjectName()).build())
                    .collect(Collectors.toList());

            return new ApiError().buildApiError(
                            ApiError.
                            builder().
                            timestamp(LocalDateTime.now()).
                            status(HttpStatus.BAD_REQUEST).subErrors(subErrors).build());

        }
    }
}
