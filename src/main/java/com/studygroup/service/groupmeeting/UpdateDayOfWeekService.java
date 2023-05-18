package com.studygroup.service.groupmeeting;

import com.studygroup.entity.GroupMeeting;
import com.studygroup.entity.StudyGroup;

import java.time.DayOfWeek;
import java.util.List;

public interface UpdateDayOfWeekService {
    void
    update( GroupMeeting groupMeeting, List<DayOfWeek> dayOfWeekList);
}
