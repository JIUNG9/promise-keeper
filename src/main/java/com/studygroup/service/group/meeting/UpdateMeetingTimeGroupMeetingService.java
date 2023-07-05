package com.studygroup.service.group.meeting;

import com.studygroup.domain.GroupMeeting;
import com.studygroup.repository.GroupMeetingRepository;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
