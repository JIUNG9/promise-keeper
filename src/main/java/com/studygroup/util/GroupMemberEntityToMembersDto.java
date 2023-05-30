package com.studygroup.util;

import com.studygroup.dto.GroupMembersDto;
import com.studygroup.entity.StudyGroupMember;

public class GroupMemberEntityToMembersDto {

    public static GroupMembersDto convert(StudyGroupMember studyGroupMember){
        return GroupMembersDto.
                builder().
                info(studyGroupMember.getIntro()).
                nickName(studyGroupMember.getNickName()).
                warnCount(studyGroupMember.getWarnCount()).
                build();

    }
}
