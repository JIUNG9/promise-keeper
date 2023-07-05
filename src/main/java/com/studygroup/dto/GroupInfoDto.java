package com.studygroup.dto;

import com.studygroup.enums.MainCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupInfoDto {

  private MainCategory mainCategory;
  private String subject;
  private String name;
  private String intro;
  private int theNumberOfMember;
}
