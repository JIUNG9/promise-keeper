package com.studygroup.util;

import com.studygroup.dto.GroupDto;
import com.studygroup.entity.StudyGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupToDto {

    public static List<GroupDto> convert(List<StudyGroup> studyGroupList){
       return studyGroupList.stream().map(GroupToDto::convert).collect(Collectors.toList());
    }

    public static GroupDto convert(StudyGroup studyGroup){

        return GroupDto.
                    builder().
                        intro(studyGroup.getInfo()).
                        theNumberOfMember(studyGroup.getStudyGroupMemberList().size()).
                        mainCategory(studyGroup.getMainCategory()).
                        name(studyGroup.getName()).
                        subject(studyGroup.getSubject()).
                        build();
    }
}
