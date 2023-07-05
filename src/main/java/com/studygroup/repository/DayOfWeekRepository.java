package com.studygroup.repository;

import com.studygroup.domain.GroupMeeting;
import com.studygroup.domain.MeetingDay;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayOfWeekRepository extends JpaRepository<MeetingDay, Long> {

  List<MeetingDay> findByGroupMeeting(GroupMeeting groupMeeting);
}
