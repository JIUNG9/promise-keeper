package com.studygroup.dto;

import com.studygroup.domain.StudyGroupMember;

public class GroupMemberEntityToMembersDto {

  public static GroupMembersDto convert(StudyGroupMember studyGroupMember) {
    return GroupMembersDto.
        builder().
        info(studyGroupMember.getIntro()).
        nickName(studyGroupMember.getNickName()).
        warnCount(studyGroupMember.getWarnCount()).
        build();

  }
}
