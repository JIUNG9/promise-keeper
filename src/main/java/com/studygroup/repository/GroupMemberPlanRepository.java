package com.studygroup.repository;

import com.studygroup.domain.StudyGroupMember;
import com.studygroup.domain.StudyGroupMemberPlan;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMemberPlanRepository extends JpaRepository<StudyGroupMemberPlan, Long> {

  Optional<StudyGroupMemberPlan> findByTitleAndEndDateAndStartDate(String title,
      Date startDate, Date endDate);

  Optional<StudyGroupMemberPlan> findByStudyGroupMemberAndId(
      StudyGroupMember studyGroupMember, Long id);
}
