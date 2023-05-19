package com.studygroup.repository;

import com.studygroup.entity.GroupMeeting;
import com.studygroup.entity.MeetingDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayOfWeekRepository extends JpaRepository<MeetingDay, Long>{
    List<MeetingDay> findByGroupMeeting(GroupMeeting groupMeeting);
}
