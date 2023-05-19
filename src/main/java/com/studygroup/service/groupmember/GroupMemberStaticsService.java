package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroup;
import org.springframework.stereotype.Service;

@Service
public interface GroupMemberStaticsService {
    int getStatics(StudyGroup studyGroup);
}
