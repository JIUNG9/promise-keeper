package com.studygroup.exception;

import org.springframework.security.core.AuthenticationException;

public class GroupMemberAuthorizationException extends AuthenticationException {

  public GroupMemberAuthorizationException(String msg) {
    super(msg);
  }
}
