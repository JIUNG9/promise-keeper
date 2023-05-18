package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroupMember;
import org.springframework.stereotype.Service;

@Service
public interface TakeOverGroupAdminService {
    public void takeOver(StudyGroupMember oldGroupAdmin, StudyGroupMember newGroupAdmin);
}
