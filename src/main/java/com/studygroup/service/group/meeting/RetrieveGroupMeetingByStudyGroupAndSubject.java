package com.studygroup.service.group.meeting;

import com.studygroup.domain.GroupMeeting;
import com.studygroup.domain.StudyGroup;
import org.springframework.stereotype.Service;

@Service
public interface RetrieveGroupMeetingByStudyGroupAndSubject {

  GroupMeeting get(StudyGroup studyGroup, String subject);
}
