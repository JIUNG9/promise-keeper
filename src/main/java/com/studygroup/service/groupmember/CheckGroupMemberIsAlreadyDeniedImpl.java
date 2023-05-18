package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroup;
import com.studygroup.entity.StudyGroupMember;
import com.studygroup.enums.GroupRole;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckGroupMemberIsAlreadyDeniedImpl implements CheckGroupMemberIsAlreadyDenied {
    private final GroupMemberRepository groupMemberRepository;
    @Override
    public boolean isAlreadyDenied(StudyGroupMember studyGroupMember) {

            if(studyGroupMember.getGroupRole().equals(GroupRole.GROUP_DENIED)){
                return true;
        }
            return false;

    }
}
