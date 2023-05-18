package com.studygroup.exception;

import org.springframework.security.core.AuthenticationException;

public class GroupAdminAuthorizationException extends AuthenticationException {
    public GroupAdminAuthorizationException(String msg) {
        super(msg);
    }
}
