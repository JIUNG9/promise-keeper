package com.studygroup.exception;

import com.studygroup.util.error.ErrorCode;

public class CustomIllegalArgumentException extends RuntimeException {

  private final ErrorCode errorCode;

  public CustomIllegalArgumentException(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
