package com.studygroup.service.groupmember;

import com.studygroup.dto.GroupApplicationList;
import com.studygroup.entity.StudyGroup;
import com.studygroup.entity.StudyGroupMember;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RetrieveGroupMembersApplicationService {
    public List<GroupApplicationList> getApplications(StudyGroup studyGroup);
}
