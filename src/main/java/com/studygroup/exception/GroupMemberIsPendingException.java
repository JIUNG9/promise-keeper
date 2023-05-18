package com.studygroup.exception;

import org.springframework.security.core.AuthenticationException;

public class GroupMemberIsPendingException extends AuthenticationException {
    public GroupMemberIsPendingException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public GroupMemberIsPendingException(String msg) {
        super(msg);
    }
}
