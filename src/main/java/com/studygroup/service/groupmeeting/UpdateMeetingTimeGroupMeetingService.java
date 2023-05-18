package com.studygroup.service.groupmeeting;

import com.studygroup.entity.GroupMeeting;

import com.studygroup.repository.GroupMeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



import java.time.LocalTime;




@RequiredArgsConstructor
@Service("UpdateMeetingTimeGroupMeeting")
public class UpdateMeetingTimeGroupMeetingService implements UpdateGroupMeetingTime {

    private final GroupMeetingRepository groupMeetingRepository;



    @Override
    public void update(GroupMeeting groupMeeting, LocalTime startTime, LocalTime endTime) {

        GroupMeeting updatedTimeMeeting =
                GroupMeeting.
                        builder().
                        id(groupMeeting.getId()).
                        dayOfWeekList(groupMeeting.getDayOfWeekList()).
                        meetingStartTime(startTime).
                        meetingEndTime(endTime).
                        dayOfWeekList(groupMeeting.getDayOfWeekList()).
                        studyGroup(groupMeeting.getStudyGroup()).
                        intro(groupMeeting.getIntro()).
                        subject(groupMeeting.getSubject()).
                        build();

        groupMeetingRepository.save(updatedTimeMeeting);
    }


}
