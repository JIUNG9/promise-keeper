package com.studygroup.util;

import com.studygroup.entity.StudyGroup;
import com.studygroup.entity.StudyGroupMember;

import java.util.List;
import java.util.stream.Collectors;

public class GroupEntityToGroupInfoDto {

    public static List<GroupInfoDto> convert(List<StudyGroup> studyGroupList){
       return studyGroupList.stream().map(GroupEntityToGroupInfoDto::convert).collect(Collectors.toList());
    }

    public static GroupInfoDto convert(StudyGroup studyGroup){
        return GroupInfoDto.
                    builder().
                        intro(studyGroup.getInfo()).
                        theNumberOfMember(studyGroup.getStudyGroupMemberList().size()).
                        mainCategory(studyGroup.getMainCategory()).
                        name(studyGroup.getName()).
                        subject(studyGroup.getSubject()).
                        build();
    }

    public static GroupInfoDto convert(StudyGroupMember studyGroupMember) {
        StudyGroup studyGroup = studyGroupMember.getStudyGroup();
        return GroupInfoDto.
                builder().
                intro(studyGroup.getInfo()).
                theNumberOfMember(studyGroup.getStudyGroupMemberList().size()).
                mainCategory(studyGroup.getMainCategory()).
                name(studyGroup.getName()).
                subject(studyGroup.getSubject()).
                build();
    }
}
