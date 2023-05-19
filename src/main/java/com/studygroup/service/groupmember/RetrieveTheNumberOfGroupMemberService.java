package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroup;
import org.springframework.stereotype.Service;

@Service
public interface RetrieveTheNumberOfGroupMemberService {
    public int getTheNumberOfGroupMember(StudyGroup studyGroup);
}
