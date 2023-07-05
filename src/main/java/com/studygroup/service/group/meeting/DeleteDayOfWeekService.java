package com.studygroup.service.group.meeting;

import com.studygroup.domain.GroupMeeting;
import com.studygroup.domain.MeetingDay;
import com.studygroup.repository.DayOfWeekRepository;
import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("DeleteDayOfWeekService")
@RequiredArgsConstructor
public class DeleteDayOfWeekService implements UpdateDayOfWeekService {

  private final DayOfWeekRepository dayOfWeekRepository;

  @Override
  public void update(GroupMeeting groupMeeting, List<DayOfWeek> dayOfWeekList) {

    List<MeetingDay> currentDayOfWeeks =
        dayOfWeekRepository.
            findByGroupMeeting(groupMeeting);

    List<MeetingDay> willBeDeletedDayOfWeeks = currentDayOfWeeks.
        stream().
        filter(d -> dayOfWeekList.contains(d.getDayOfWeek())).
        collect(Collectors.toList());

    dayOfWeekRepository.deleteAll(willBeDeletedDayOfWeeks);
  }
}
