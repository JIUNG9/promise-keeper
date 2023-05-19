package com.studygroup.exception;

import com.studygroup.util.constant.ErrorCode;

public class CustomIllegalArgumentException extends RuntimeException {

    private ErrorCode errorCode;

    public CustomIllegalArgumentException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
