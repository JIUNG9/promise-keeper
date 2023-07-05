package com.studygroup.exception;

import com.studygroup.util.error.ErrorCode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionHandler {

  @Order(Ordered.HIGHEST_PRECEDENCE + 4)
  @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
  protected ResponseEntity<Object> Exception(Exception ex) {
    return ApiError.buildApiError(
        ApiError.
            builder().
            message(ex.getMessage()).
            timestamp(LocalDateTime.now()).
            status(HttpStatus.BAD_REQUEST).
            build());
  }

  @Order(Ordered.HIGHEST_PRECEDENCE + 3)
  @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
  protected ResponseEntity<Object> jsonParserErrorHandler(HttpMessageNotReadableException ex) {
    return ApiError.buildApiError(
        ApiError.
            builder().
            message(ErrorCode.JSON_PARSER_ERROR.getMessage()).
            code(ErrorCode.JSON_PARSER_ERROR.getCode()).
            timestamp(LocalDateTime.now()).
            status(HttpStatus.BAD_REQUEST).
            build());
  }

  @Order(Ordered.HIGHEST_PRECEDENCE + 2)
  @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<Object> enumTypeIsUnMatched(MethodArgumentTypeMismatchException ex) {
    return ApiError.buildApiError(
        ApiError.
            builder().
            message(ErrorCode.ENUM_TYPE_IS_MISMATCHED.getMessage() + ex.getName()).
            code(ErrorCode.ENUM_TYPE_IS_MISMATCHED.getCode()).
            timestamp(LocalDateTime.now()).
            status(HttpStatus.NOT_FOUND).
            build());
  }

  @Order(Ordered.HIGHEST_PRECEDENCE + 1)
  @org.springframework.web.bind.annotation.ExceptionHandler(CustomIllegalArgumentException.class)
  protected ResponseEntity<Object> resourceNotFoundException(CustomIllegalArgumentException ex) {
    return ApiError.buildApiError(
        ApiError.
            builder().
            message(ex.getErrorCode().getMessage()).
            code(ex.getErrorCode().getCode()).
            timestamp(LocalDateTime.now()).
            status(HttpStatus.NOT_FOUND).
            build());
  }


  @Order(Ordered.HIGHEST_PRECEDENCE)
  @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
  private ResponseEntity<Object> ValidationError(MethodArgumentNotValidException ex) {

    List<ApiSubError> subErrors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(x -> ApiValidationError.
            builder().
            message(x.getDefaultMessage()).
            rejectedValue(x.getRejectedValue()).
            field(x.getField()).
            object(x.getObjectName()).
            build())
        .collect(Collectors.toList());

    return new ApiError().buildApiError(
        ApiError.
            builder().
            timestamp(LocalDateTime.now()).
            status(HttpStatus.UNPROCESSABLE_ENTITY).
            code(ErrorCode.VALIDATION_ERROR.getCode()).
            subErrors(subErrors).
            build());

  }

}
