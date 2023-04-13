package com.studygroup.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    CAN_NOT_WITHDRAWAL("Email and password are not matched so can not withdrawal the account"),
    SIGN_UP_FAIL("user input information is not valid"),
    EMAIL_NOT_VERIFIED("Email is not verified"),
    THERE_IS_NO_EMAIL("There is no email as user input"),
    LOGIN_FAIL("Email and password are not matched"),
    PASSWORD_REGEX("input the "),
    EMAIL_IS_DUPLICATED("Email address is duplicated");

    private final String message;


}
