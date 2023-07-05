package com.studygroup.util.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

  // authorization error
  YOU_ARE_NOT_ADMIN("0001", "관리자가 아닙니다."),
  YOU_ARE_NOT_GROUP_ADMIN("0002", "그룹 관리자가 아닙니다."),
  YOU_ARE_NOT_GROUP_MEMBER("0003", "그룹 회원이 아닙니다."),
  AUTHORIZATION_ERROR("0004", "인증이 완료 되지 않았습니다."),
  JWT_TOKEN_NOT_VALID("0005", "유효하지 않은 로그인 접근입니다."),
  JWT_TOKEN_EMPTY("0006", "로그인을 하십시오"),
  JWT_TOKEN_EXPIRED("0007", "로그인 시간이 만료 되었습니다."),
  JWT_TOKEN_EXTEND_FAILED("0008","이미 로그인 시간이 만료되었거나 올바르지 않은 접근입니다."),


  //user error
  CAN_NOT_WITHDRAWAL("1000", "아이디와 패스워드가 일치하지 않아 회원탈퇴가 불가능합니다."),
  SIGN_UP_FAIL("1001", "입력한 정보를 다시 확인해주십시오"),
  EMAIL_NOT_VERIFIED("1002", "이메일이 인증되지 않았습니다."),
  EMAIL_VERIFICATION_TOKEN_ERROR("1003", "이메일 토큰 정보가 일치하지 않습니다. 최근의 발송된 이메일인지 확인해주세요."),
  EMAIL_IS_NOT_EXISTED("1004", "존재하지 않은 이메일입니다."),
  EMAIL_IS_DUPLICATED("1005", "중복된 이메일입니다."),
  LOGIN_FAIL("1006", "로그인이 실패했습니다. 아이디 패스워드를 확인해주세요."),
  NO_USER_ID("1007", "존재하지 않는 아이디입니다."),
  YOU_ARE_KICKED("1008", "강퇴된 회원입니다."),
  ALREADY_EMAIL_VERIFIED("1009", "이미 인증이 완료되었습니다."),

  //group error
  GROUP_MEETING_SUBJECT_IS_NOT_EXISTED("2000",
      "존재하지 않은 그룹 미팅 주제입니다."),
  GROUP_MEETING_SUBJECT_IS_DUPLICATED("2001", "중복된 그룹 미팅 주제입니다."),
  GROUP_IS_NOT_EXISTED_BY_NAME("2002", "해당 그룹 이름은 존재하지 않습니다."),
  GROUP_NAME_IS_DUPLICATED("2003", "해당 그룹 이름은 이미 존재합니다."),
  GROUP_MEMBER_IS_NOT_EXISTED("2005", "존재하지 않은 그룹 멤버입니다."),
  CAN_NOT_DELETE_GROUP("2006", "그룹에 회원이 존재하여 그룹을 삭제할 수 없습니다."),
  GROUP_NICK_NAME_IS_DUPLICATED("2007", "해당 닉네임은 해당 그룹에 존재합니다. 다른 닉네임을 사용해주세요"),
  YOU_ARE_NOW_PENDING_STATUS("2008", "그룹 관리자의 승인을 기다려주십시오 알림 메세지를 보냈습니다."),
  YOU_ARE_DENIED_FROM_GROUP_ADMIN("2009", "그룹 관리자에게 가입이 거부 되었습니다."),
  TAKE_OVER_AUTHORITY_TO_GROUP_MEMBER_BEFORE_OUT("2010",
      "그룹의 관리자는 탈퇴 이전 그룹의 멤버에게 권한을 넘겨야합니다."),
  GROUP_MEMBER_IS_NOT_QUALIFIED_BE_ADMIN("2011",
      "해당 그룹 회원은 가입 승인 대기 중이거나 거부 된 회원입니다."),
  CAN_NOT_KICK_GROUP_MEMBER_WARN_COUNT_IS_UNDER_10("2012",
      "해당 그룹 회원은 경고 횟수가 10회 미만이라 그룹에서 탈퇴를 시킬 수 없습니다."),

  //plan error
  PLAN_IS_ALREADY_EXIST("4000", "중복된 계획입니다."),
  PLAN_IS_NOT_EXIST("4001", "존재하지 않은 계획입니다."),

  //parse error
  SAVE_ERROR("5000", "처리할 수 없는 정보입니다."),
  ENUM_TYPE_IS_MISMATCHED("5001", "체크리스트를 다시 확인해주십시오"),
  JSON_PARSER_ERROR("5002", "입력한 정보를 다시 확인해주세요."),

  //Chat error
  USER_IS_NOT_IN_CHAT_ROOM("7000", "채팅방에 입장하지 않았습니다."),
  CHAT_ROOM_IS_ALREADY_EXISTED("7001", "이미 생성된 채팅방입니다."),
  CHAT_ROOM_IS_NOT_EXISTED("7002", "존재하지 않은 채팅방입니다."),
  RABBITMQ_SERVER_ERROR("7003", "처리할 수 없는 메세지입니다."),

  //validation error
  VALIDATION_ERROR("8000", "입력한 정보를 다시 한 번 확인해주세요.");

  private final String code;
  private final String message;


}
