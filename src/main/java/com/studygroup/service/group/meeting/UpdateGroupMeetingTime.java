package com.studygroup.service.group.meeting;

import com.studygroup.domain.GroupMeeting;
import java.time.LocalTime;

public interface UpdateGroupMeetingTime {

  void update(GroupMeeting groupMeeting, LocalTime startTime, LocalTime endTime);

}
