package com.studygroup.repository;

import com.studygroup.entity.GroupMeeting;
import com.studygroup.entity.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMeetingRepository extends JpaRepository<GroupMeeting,Long> {

    GroupMeeting findByStudyGroupAndSubject(StudyGroup studyGroup, String subject);
    GroupMeeting findBySubject(String subject);
}
