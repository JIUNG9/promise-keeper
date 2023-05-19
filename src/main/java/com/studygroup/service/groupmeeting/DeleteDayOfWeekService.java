package com.studygroup.service.groupmeeting;

import com.studygroup.entity.GroupMeeting;
import com.studygroup.entity.StudyGroup;
import com.studygroup.repository.DayOfWeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

@Service("DeleteDayOfWeekService")
@RequiredArgsConstructor
public class DeleteDayOfWeekService implements UpdateDayOfWeekService {

    private final DayOfWeekRepository dayOfWeekRepository;

    @Override
    public void update(GroupMeeting groupMeeting, List<DayOfWeek> dayOfWeekList) {

        List<com.studygroup.entity.MeetingDay> currentDayOfWeeks =
                dayOfWeekRepository.
                        findByGroupMeeting(groupMeeting);


        List<com.studygroup.entity.MeetingDay> willBeDeletedDayOfWeeks = currentDayOfWeeks.
                stream().
                filter(d -> dayOfWeekList.contains(d.getDayOfWeek())).
                collect(Collectors.toList());

        dayOfWeekRepository.deleteAll(willBeDeletedDayOfWeeks);
    }
}
