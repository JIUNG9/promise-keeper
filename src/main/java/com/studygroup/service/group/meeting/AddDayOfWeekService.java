package com.studygroup.service.group.meeting;

import com.studygroup.domain.GroupMeeting;
import com.studygroup.domain.MeetingDay;
import com.studygroup.repository.DayOfWeekRepository;
import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("AddDayOfWeekService")
@RequiredArgsConstructor
public class AddDayOfWeekService implements UpdateDayOfWeekService {

  private final DayOfWeekRepository dayOfWeekRepository;

  @Override
  public void update(
      GroupMeeting groupMeeting,
      List<DayOfWeek> dayOfWeekList) {

    List<MeetingDay> currentDayOfWeeks =
        dayOfWeekRepository.
            findByGroupMeeting(groupMeeting);

    List<java.time.DayOfWeek> currentDayOfWeeksAsTime =
        currentDayOfWeeks.stream()
            .map(MeetingDay::getDayOfWeek)
            .collect(Collectors.toList());

    List<java.time.DayOfWeek> noDuplicatedDayOfWeek =
        dayOfWeekList.stream()
            .filter(d -> !currentDayOfWeeksAsTime.contains(d))
            .collect(Collectors.toList());

    List<MeetingDay> noDuplicatedDayOfWeekEntities =
        noDuplicatedDayOfWeek.stream()
            .map(d -> MeetingDay.
                builder().
                dayOfWeek(d).
                groupMeeting(groupMeeting).
                build())
            .collect(Collectors.toList());

    dayOfWeekRepository.saveAll(noDuplicatedDayOfWeekEntities);

  }
}
