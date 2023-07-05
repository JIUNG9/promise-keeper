package com.studygroup.service.group.plan;

import com.studygroup.domain.StudyGroupMemberPlan;
import java.util.Date;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface CheckPlanIsExistService {

  Optional<StudyGroupMemberPlan> find(String title, Date startDate, Date endDate);
}
