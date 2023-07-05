package com.studygroup.dto.member;

import com.studygroup.enums.Gender;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class UserInfoDto {

  String email;
  String name;
  int age;
  Gender gender;
  List<String> groupName;
  List<String> groupNickName;
}
