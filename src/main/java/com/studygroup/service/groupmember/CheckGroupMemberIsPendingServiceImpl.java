package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroupMember;
import com.studygroup.enums.GroupRole;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("CheckGroupMemberIsPendingServiceImpl")
@RequiredArgsConstructor
public class CheckGroupMemberIsPendingServiceImpl implements CheckGroupMemberIsPendingService {
    private final GroupMemberRepository groupMemberRepository;
    @Override
    public boolean isAlreadyApplied(StudyGroupMember studyGroupMember) {
        if(studyGroupMember.getGroupRole().equals(GroupRole.GROUP_PENDING)){
            return true;
        }
        return false;
    }
}
