package com.studygroup.service.group.meeting;

import com.studygroup.domain.GroupMeeting;
import java.time.DayOfWeek;
import java.util.List;

public interface UpdateDayOfWeekService {

  void
  update(GroupMeeting groupMeeting, List<DayOfWeek> dayOfWeekList);
}
