package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroup;
import com.studygroup.entity.StudyGroupMember;
import org.springframework.stereotype.Service;

@Service
public interface RetrieveGroupMemberByNickNameAndGroupService {
    public StudyGroupMember get(StudyGroup studyGroup, String nickName);
}
