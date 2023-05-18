package com.studygroup.service.groupmeeting;

import com.studygroup.entity.GroupMeeting;
import com.studygroup.entity.StudyGroup;
import com.studygroup.repository.DayOfWeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service("AddDayOfWeekService")
@RequiredArgsConstructor
public class AddDayOfWeekService implements UpdateDayOfWeekService {

    private final DayOfWeekRepository dayOfWeekRepository;

    @Override
    public void update(
                       GroupMeeting groupMeeting,
                       List<DayOfWeek> dayOfWeekList) {

        List<com.studygroup.entity.MeetingDay> currentDayOfWeeks =
                dayOfWeekRepository.
                        findByGroupMeeting(groupMeeting);

        List<java.time.DayOfWeek> currentDayOfWeeksAsTime =
                currentDayOfWeeks.stream()
                        .map(com.studygroup.entity.MeetingDay::getDayOfWeek)
                        .collect(Collectors.toList());

        List<java.time.DayOfWeek> noDuplicatedDayOfWeek =
                dayOfWeekList.stream()
                        .filter(d -> !currentDayOfWeeksAsTime.contains(d))
                        .collect(Collectors.toList());

        List<com.studygroup.entity.MeetingDay> noDuplicatedDayOfWeekEntities =
                noDuplicatedDayOfWeek.stream()
                        .map(d -> com.studygroup.entity.MeetingDay.
                                builder().
                                dayOfWeek(d).
                                groupMeeting(groupMeeting).
                                build())
                        .collect(Collectors.toList());



        dayOfWeekRepository.saveAll(noDuplicatedDayOfWeekEntities);

    }
}
