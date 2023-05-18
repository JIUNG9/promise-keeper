package com.studygroup.service.groupmeeting;

import com.studygroup.dto.CreateGroupMeetingForm;
import com.studygroup.entity.GroupMeeting;
import com.studygroup.entity.StudyGroup;
import org.springframework.stereotype.Service;

@Service
public interface CreateGroupMeetingService {
    void create(CreateGroupMeetingForm createGroupMeetingForm, StudyGroup studyGroup);
}
