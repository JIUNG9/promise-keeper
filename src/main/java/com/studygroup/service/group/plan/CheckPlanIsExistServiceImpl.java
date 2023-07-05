package com.studygroup.service.group.plan;

import com.studygroup.domain.StudyGroupMemberPlan;
import com.studygroup.repository.GroupMemberPlanRepository;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckPlanIsExistServiceImpl implements CheckPlanIsExistService {

  private final GroupMemberPlanRepository groupMemberPlanRepository;

  @Override
  public Optional<StudyGroupMemberPlan> find(String title, Date startDate, Date endDate) {
    return groupMemberPlanRepository.findByTitleAndEndDateAndStartDate(title, startDate, endDate);
  }
}
