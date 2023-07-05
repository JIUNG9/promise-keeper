package com.studygroup.repository;

import com.studygroup.domain.GroupMeeting;
import com.studygroup.domain.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMeetingRepository extends JpaRepository<GroupMeeting, Long> {

  GroupMeeting findByStudyGroupAndSubject(StudyGroup studyGroup, String subject);

  GroupMeeting findBySubject(String subject);
}
