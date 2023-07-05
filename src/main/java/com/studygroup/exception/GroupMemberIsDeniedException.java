package com.studygroup.exception;

import org.springframework.security.core.AuthenticationException;

public class GroupMemberIsDeniedException extends AuthenticationException {

  public GroupMemberIsDeniedException(String msg, Throwable cause) {
    super(msg, cause);
  }


  public GroupMemberIsDeniedException(String msg) {
    super(msg);
  }
}
