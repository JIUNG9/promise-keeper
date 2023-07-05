package com.studygroup.service.group.meeting;

import com.studygroup.dto.CreateGroupMeetingForm;
import com.studygroup.domain.StudyGroup;
import org.springframework.stereotype.Service;

@Service
public interface CreateGroupMeetingService {

  void create(CreateGroupMeetingForm createGroupMeetingForm, StudyGroup studyGroup);
}
