package com.studygroup.controller;

import com.studygroup.dto.MemberSignUpForm;

import com.studygroup.dto.PasswordUpdateForm;
import com.studygroup.entity.Member;
import com.studygroup.exception.ApiError;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.service.CheckDuplicationService;
import com.studygroup.service.user.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.studygroup.util.CookieUtil;
import com.studygroup.util.JwtUtil;
import com.studygroup.util.RandomPasswordGenerator;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.constant.LoginExpirationTime;
import com.studygroup.util.constant.ObjectToLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class UserController {

    private final UserRemovalService withdrawalUserAsAdminService;
    private final CheckDuplicationService checkEmailDuplicationService;
    private final UserRemovalService userDeletionService;
    private final RetrieveMemberByToken retrieveMemberByVerificationTokenService;
    private final RetrieveMemberByToken retrieveMemberServiceByPasswordResetTokenService;
    private final RetrieveMemberByIdService retrieveMemberByIdService;
    private final RetrieveMemberByEmail retrieveMemberByEmailService;
    private final UpdateUserIsEnableService updateUserIsEnableService;
    private final UpdateUserPasswordService updatePasswordService;
    private final VerifyTheTokenService verifyUserValidateToken;
    private final VerifyTheTokenService verifyPasswordResetToken;
    private final SignUpService signUpService;
    private final LogoutService logoutService;
    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);

    public UserController(
            @Qualifier("UserKickService") UserRemovalService withdrawalUserAsAdminService,
            @Qualifier("UserDeletionService") UserRemovalService userDeletionService,
            @Qualifier("CheckEmailDuplicationService") CheckDuplicationService checkEmailDuplicationService,
            @Qualifier("RetrieveMemberByVerificationTokenService")RetrieveMemberByToken retrieveMemberByVerificationTokenService,
            @Qualifier("RetrieveMemberServiceByPasswordResetTokenService")RetrieveMemberByToken retrieveMemberServiceByPasswordResetTokenService,
                          RetrieveMemberByIdService retrieveMemberByIdService,
            @Qualifier("RetrieveMemberByEmailService") RetrieveMemberByEmail retrieveMemberByEmailService,
                          UpdateUserIsEnableService updateUserIsEnableService,
            @Qualifier("UpdatePasswordService")UpdateUserPasswordService updatePasswordService,
            @Qualifier("VerifyUserValidateTokenService")VerifyTheTokenService verifyUserValidateToken,
            @Qualifier("VerifyPasswordResetTokenService")VerifyTheTokenService verifyPasswordResetToken,
                          SignUpService signUpService,
                          LogoutService logoutService) {
        this.withdrawalUserAsAdminService = withdrawalUserAsAdminService;
        this.checkEmailDuplicationService = checkEmailDuplicationService;
        this.userDeletionService = userDeletionService;
        this.retrieveMemberByVerificationTokenService = retrieveMemberByVerificationTokenService;
        this.retrieveMemberServiceByPasswordResetTokenService = retrieveMemberServiceByPasswordResetTokenService;
        this.retrieveMemberByIdService = retrieveMemberByIdService;
        this.retrieveMemberByEmailService = retrieveMemberByEmailService;
        this.updateUserIsEnableService = updateUserIsEnableService;
        this.updatePasswordService = updatePasswordService;
        this.verifyUserValidateToken = verifyUserValidateToken;
        this.verifyPasswordResetToken = verifyPasswordResetToken;
        this.signUpService = signUpService;
        this.logoutService = logoutService;
    }

    @PostMapping("/api/users")
    public ResponseEntity<Object> userSave(@RequestBody @Valid MemberSignUpForm memberForm) {

        Member member = Member.
                builder().
                email(memberForm.getEmail()).
                name(memberForm.getName()).
                password(memberForm.getPassword()).
                gender(memberForm.getGender()).
                age(memberForm.getAge()).
                build();
        if(checkEmailDuplicationService.isDuplicated(member.getEmail())){
            logger.info("email is duplicated");

            return ApiError.
                            buildApiError(ErrorCode.
                                    EMAIL_IS_DUPLICATED,
                                    HttpStatus.CONFLICT);
        }
        signUpService.signUp(member);
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body("User is successfully saved but need to verify the email");


    }



    @PostMapping("/api/users/refresh-token")
    public ResponseEntity<Object> extendTheJwtToken(HttpServletRequest request,
                                                    HttpServletResponse response) {

        String oldToken = CookieUtil.getValue(request,"jwtToken");
        String refreshedToken = JwtUtil.refreshToken(oldToken);

        Cookie refreshCookie = CookieUtil.create("jwtToken",
                                refreshedToken,
                                LoginExpirationTime.LOGIN_EXPIRATION_TIME,
                        "localhost");

        response.addCookie(refreshCookie);

        return ResponseEntity.
                status(HttpStatus.CREATED).
                body("successfully extend the token into 30min");

    }
    @PostMapping("/api/users/log-out")
    public ResponseEntity<Object> userLogout(HttpServletRequest request,
                                             HttpServletResponse response) {

        logoutService.logout(request,response,"jwtToken");
        return ResponseEntity.
                status(HttpStatus.OK).
                body("log-out is succeeded");
    }

    @PutMapping("/api/auth/password-reset/{token}")
    public ResponseEntity<Object> userResetPassword(@PathVariable String token) {

        try {
            verifyPasswordResetToken.verifyTheToken(token);
        }
        catch (CustomIllegalArgumentException e){

            logger.info("token is not valid");
            return ApiError.
                    buildApiError(
                            e.getErrorCode(),
                            HttpStatus.NOT_FOUND);
        }

        Member member = retrieveMemberServiceByPasswordResetTokenService.getMember(token);
        String tempPassword = RandomPasswordGenerator.generateRandomPassword();
            updatePasswordService.update(member,tempPassword);
            return ResponseEntity.
                    status(HttpStatus.OK).
                    body("this is the temp password: " + tempPassword);

        }




    @PutMapping("/api/auth/email-verification/{token}")
    public ResponseEntity<Object> userVerifyTheEmail(@PathVariable String token) {

        try {
            verifyUserValidateToken.verifyTheToken(token);
        } catch (CustomIllegalArgumentException e) {
            logger.info("token is not valid");

            return ApiError.buildApiError(
                    e.getErrorCode(),
                    HttpStatus.NOT_FOUND);

        }

        Member member = retrieveMemberByVerificationTokenService.getMember(token);

        //already verified
        if(member.isEnabled()){
            logger.info("User is already verified");
            return ApiError.buildApiError(
                    ErrorCode.ALREADY_EMAIL_VERIFIED,
                    HttpStatus.FORBIDDEN);
        }
        else {
            updateUserIsEnableService.update(member, true);
            return ResponseEntity.
                    status(HttpStatus.OK).
                    body("email verification is succeeded");
        }



    }

    @PutMapping("/api/users/info/password")
    public ResponseEntity<Object> updatePassword(@AuthenticationPrincipal Object memberId,
                                                 @RequestBody @Valid PasswordUpdateForm passwordUpdateForm) {

            Member member = retrieveMemberByIdService.getMember(ObjectToLong.convert(memberId));
            updatePasswordService.update(member, passwordUpdateForm.getPassword());

        return ResponseEntity.
                status(HttpStatus.OK).
                body("update password is succeeded ");

    }

    @DeleteMapping("/api/users/info/{email}")
    public ResponseEntity<Object> withdrawalUserSelf(@AuthenticationPrincipal Object memberId,
                                                     @PathVariable String email,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response) {

        Member member = retrieveMemberByEmailService.getMember(email);

        if(member.getId() == Long.parseLong(memberId.toString())){
            userDeletionService.removeUser(member);
            logoutService.logout(request,response,"jwtToken");
        }
        return ResponseEntity.
                status(HttpStatus.OK).
                body("Delete user is succeeded");

    }

    @DeleteMapping("/api/admins/{userId}")
    public ResponseEntity<Object> kickUserAsAdmin(@PathVariable Long userId) {

        Member member = retrieveMemberByIdService.getMember(userId);
        logger.info(member.toString());

        withdrawalUserAsAdminService.removeUser(member);

        return ResponseEntity.
                status(HttpStatus.OK).
                body("Kick the user is succeeded ");

    }

}
