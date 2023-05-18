package com.studygroup.service.groupmeeting;

import com.studygroup.entity.GroupMeeting;
import org.springframework.stereotype.Service;

@Service
public interface DeleteGroupMeetingService {
    public void delete(GroupMeeting groupMeeting);
}
