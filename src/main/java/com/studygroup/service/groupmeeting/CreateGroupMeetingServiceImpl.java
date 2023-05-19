package com.studygroup.service.groupmeeting;

import com.studygroup.dto.CreateGroupMeetingForm;
import com.studygroup.entity.GroupMeeting;
import com.studygroup.entity.MeetingDay;
import com.studygroup.entity.StudyGroup;
import com.studygroup.repository.DayOfWeekRepository;
import com.studygroup.repository.GroupMeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;

@Service("CreateGroupMeetingServiceImpl")
@RequiredArgsConstructor
public class CreateGroupMeetingServiceImpl implements CreateGroupMeetingService {

    private final GroupMeetingRepository groupMeetingRepository;
    private final DayOfWeekRepository dayOfWeekRepository;
    @Override
    public void create(CreateGroupMeetingForm form, StudyGroup studyGroup){

        GroupMeeting newGroupMeeting =
                GroupMeeting.
                builder().
                        meetingEndTime(form.getMeetingEndTime()).
                        meetingStartTime(form.getMeetingStartTime()).
                        intro(form.getIntro()).
                        studyGroup(studyGroup).
                        subject(form.getSubject()).
                        build();

        for(java.time.DayOfWeek d :form.getMeetingDateList()){
            MeetingDay dayOfWeek = MeetingDay.builder().dayOfWeek(d).groupMeeting(newGroupMeeting).build();
            dayOfWeekRepository.save(dayOfWeek);
        }

    }
}
