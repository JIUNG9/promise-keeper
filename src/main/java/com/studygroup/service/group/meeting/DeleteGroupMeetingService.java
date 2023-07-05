package com.studygroup.service.group.meeting;

import com.studygroup.domain.GroupMeeting;
import org.springframework.stereotype.Service;

@Service
public interface DeleteGroupMeetingService {

  void delete(GroupMeeting groupMeeting);
}
