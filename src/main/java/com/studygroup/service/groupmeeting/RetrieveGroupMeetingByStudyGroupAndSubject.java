package com.studygroup.service.groupmeeting;

import com.studygroup.entity.GroupMeeting;
import com.studygroup.entity.StudyGroup;
import org.springframework.stereotype.Service;

@Service
public interface RetrieveGroupMeetingByStudyGroupAndSubject {
    GroupMeeting get(StudyGroup studyGroup, String subject);
}
