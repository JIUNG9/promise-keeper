package com.studygroup.service.groupmember;

import com.studygroup.entity.Member;
import com.studygroup.entity.StudyGroupMember;
import org.springframework.stereotype.Service;

@Service
public interface FindGroupAdminMemberService {
    public StudyGroupMember findAdminMember(String groupName);
}
