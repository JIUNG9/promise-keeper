package com.studygroup.service.groupmeeting;

import com.studygroup.entity.GroupMeeting;
import com.studygroup.entity.StudyGroup;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public interface UpdateGroupMeetingTime {
    void update(GroupMeeting groupMeeting, LocalTime startTime, LocalTime endTime);

}
