package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroupMember;
import com.studygroup.enums.GroupMemberApplicationConSentOrDeny;
import org.springframework.stereotype.Service;

@Service
public interface HandleGroupMemberApplicationService {
    public void handle(StudyGroupMember studyGroupMember,
                       GroupMemberApplicationConSentOrDeny groupMemberApplicationConSentOrDeny);
}
