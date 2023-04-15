package com.studygroup.util.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    CAN_NOT_WITHDRAWAL("Email and password are not matched so can not withdrawal the account"),
    SIGN_UP_FAIL("user input information is not valid"),
    EMAIL_NOT_VERIFIED("Email is not verified"),
    EMAIL_VERIFICATION_TOKEN_ERROR("Token is not matched by user input"),
    EMAIL_IS_DUPLICATED("Email is duplicated enter the another one"),
    THERE_IS_NO_EMAIL("There is no email as user input"),
    LOGIN_FAIL("Email and password are not matched"),
    NO_USER_ID("There is no user with that user id"),
    SAVE_ERROR("can not save the entity");

    private final String message;


}
