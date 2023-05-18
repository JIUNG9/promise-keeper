package com.studygroup.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.studygroup.util.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private HttpStatus status;
    private int code;
    private String message;
    private List<ApiSubError> subErrors;

    public static ResponseEntity<Object> buildApiError(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    public static ResponseEntity<Object> buildApiError(ErrorCode errorCode,HttpStatus httpStatus) {

        ApiError apiError = ApiError.
                                builder().
                                status(httpStatus).
                                message(errorCode.getMessage()).
                                timestamp(LocalDateTime.now()).

                                code(errorCode.getCode()).
                                build();

    return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
