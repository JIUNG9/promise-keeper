package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroupMember;
import org.springframework.stereotype.Service;

@Service
public interface CheckGroupMemberIsPendingService {
    public boolean isAlreadyApplied(StudyGroupMember studyGroupMember);
}
