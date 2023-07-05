package com.studygroup.service.group.meeting;

import com.studygroup.dto.CreateGroupMeetingForm;
import com.studygroup.domain.GroupMeeting;
import com.studygroup.domain.MeetingDay;
import com.studygroup.domain.StudyGroup;
import com.studygroup.repository.DayOfWeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("CreateGroupMeetingServiceImpl")
@RequiredArgsConstructor
public class CreateGroupMeetingServiceImpl implements CreateGroupMeetingService {

  private final DayOfWeekRepository dayOfWeekRepository;

  @Override
  public void create(CreateGroupMeetingForm form, StudyGroup studyGroup) {

    GroupMeeting newGroupMeeting =
        GroupMeeting.
            builder().
            meetingEndTime(form.getMeetingEndTime()).
            meetingStartTime(form.getMeetingStartTime()).
            intro(form.getIntro()).
            studyGroup(studyGroup).
            subject(form.getSubject()).
            build();

    for (java.time.DayOfWeek d : form.getMeetingDateList()) {
      MeetingDay dayOfWeek = MeetingDay.builder().dayOfWeek(d).groupMeeting(newGroupMeeting)
          .build();
      dayOfWeekRepository.save(dayOfWeek);
    }
  }
}
