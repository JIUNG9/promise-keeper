package com.studygroup.controller;

import com.studygroup.auth.token.JwtToken;
import com.studygroup.auth.token.TokenData;
import com.studygroup.auth.token.TokenProcessor;
import com.studygroup.domain.Member;
import com.studygroup.dto.PasswordUpdateForm;
import com.studygroup.dto.member.MemberSignUpForm;
import com.studygroup.dto.member.UserInfoDto;
import com.studygroup.exception.ApiError;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.service.user.SignUpService;
import com.studygroup.util.convertor.ObjectToLong;
import com.studygroup.util.creator.RandomPasswordGenerator;
import com.studygroup.util.error.ErrorCode;
import com.studygroup.util.cookie.CookieUtil;
import com.studygroup.util.token.JwtUtil;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserController {

  private static final Logger logger = LoggerFactory
      .getLogger(UserController.class);
  private final SignUpService signUpService;
  private final TokenProcessor<TokenData> tokenProcessor;


  @PostMapping("/api/users")
  public ResponseEntity<Object> userCreate(@RequestBody @Valid MemberSignUpForm signUpForm) {

    signUpService.signUp(signUpForm);

    return ResponseEntity.
        status(HttpStatus.CREATED).
        body("성공적으로 회원가입이 됐습니다. 이메일 인증을 해주십시오");

  }


  @PutMapping("/api/users/me/token")
  public ResponseEntity<Object> extendTheJwtToken(HttpServletRequest request,
      HttpServletResponse response) {
    JwtToken<SimpleGrantedAuthority> oldToken = new JwtToken<>(
        CookieUtil.getValue(request, "jwtToken"));

    if (tokenProcessor.validate(oldToken)) {
      String newTokenValue = JwtUtil.refreshToken(oldToken.getValue());
      Cookie renewCookie = tokenProcessor.extend(
          new JwtToken<SimpleGrantedAuthority>(newTokenValue));
      response.addCookie(renewCookie);

      return ResponseEntity.
          status(HttpStatus.CREATED).
          body("성공적으로 로그인 시간이 연장되었습니다.");
    }
    return ResponseEntity.status(401).body(ErrorCode.JWT_TOKEN_EXTEND_FAILED);
  }

  @PostMapping("/api/users/me/token")
  public ResponseEntity<Object> userLogout(HttpServletRequest request) {
    JwtToken<SimpleGrantedAuthority> jwtToken = new JwtToken(
        CookieUtil.getValue(request, "jwtToken"));
    tokenProcessor.remove(jwtToken);
    return ResponseEntity.
        status(HttpStatus.OK).
        body("성공적으로 로그아웃 되었습니다.");
  }

  @PutMapping("/api/auth/password-reset/{token}")
  public ResponseEntity<Object> userResetPassword(@PathVariable String token) {

    try {
      verifyPasswordResetToken.verifyTheToken(token);
    } catch (CustomIllegalArgumentException e) {

      logger.info("tokenData is not valid");
      return ApiError.
          buildApiError(
              e.getErrorCode(),
              HttpStatus.NOT_FOUND);
    }

    Member member = retrieveMemberServiceByPasswordResetTokenService.getMember(token);
    String tempPassword = RandomPasswordGenerator.generateRandomPassword();
    updatePasswordService.update(member, tempPassword);
    return ResponseEntity.
        status(HttpStatus.OK).
        body("this is the temp password: " + tempPassword);

  }


  @PutMapping("/api/auth/email-verification/{token}")
  public ResponseEntity<Object> userVerifyTheEmail(@PathVariable String token) {

    try {
      verifyUserValidateToken.verifyTheToken(token);
    } catch (CustomIllegalArgumentException e) {
      logger.info("tokenData is not valid");

      return ApiError.buildApiError(
          e.getErrorCode(),
          HttpStatus.NOT_FOUND);

    }

    Member member = retrieveMemberByVerificationTokenService.getMember(token);

    //already verified
    if (member.isEnabled()) {
      logger.info("User is already verified");
      return ApiError.buildApiError(
          ErrorCode.ALREADY_EMAIL_VERIFIED,
          HttpStatus.FORBIDDEN);
    } else {
      updateUserIsEnableService.update(member, true);
      return ResponseEntity.
          status(HttpStatus.OK).
          body("email verification is succeeded");
    }


  }

  @PutMapping("/api/users/me/info/password")
  public ResponseEntity<Object> updatePassword(@AuthenticationPrincipal Object memberId,
      @RequestBody @Valid PasswordUpdateForm passwordUpdateForm) {

    Member member =
        retrieveMemberByAuthPrinciple.
            getMember(ObjectToLong.convert(memberId));
    updatePasswordService.update(member, passwordUpdateForm.getPassword());

    return ResponseEntity.
        status(HttpStatus.OK).
        body("update password is succeeded ");

  }

  @GetMapping("/api/users/me/info")
  public ResponseEntity<Object> getMyInfo(@AuthenticationPrincipal Object memberId) {

    Member member = retrieveMemberByAuthPrinciple.getMember(ObjectToLong.convert(memberId));
    UserInfoDto userInfoDto = getMyInformationService.getMyInfo(member);
    return ResponseEntity.
        status(HttpStatus.OK).
        body(userInfoDto);

  }


  @DeleteMapping("/api/users/me")
  public ResponseEntity<Object> withdrawalUserSelf(@AuthenticationPrincipal Object memberId,
      HttpServletRequest request,
      HttpServletResponse response) {

    if (member.getId() == Long.parseLong(memberId.toString())) {
      userDeletionService.removeUser(member);
      logoutService.logout(request, response, "jwtToken");
    }
    return ResponseEntity.
        status(HttpStatus.OK).
        body("Delete user is succeeded");

  }

  @DeleteMapping("/api/admins/me/users/{userId}")
  public ResponseEntity<Object> kickUserAsAdmin(@PathVariable Long userId) {

    Member member = retrieveMemberByAuthPrinciple.getMember(userId);
    logger.info(member.toString());

    withdrawalUserAsAdminService.removeUser(member);

    return ResponseEntity.
        status(HttpStatus.OK).
        body("Kick the user is succeeded ");

  }

}
