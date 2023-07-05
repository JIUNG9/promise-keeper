package com.studygroup.service.group.plan;

import com.studygroup.dto.CreatePlanForm;
import com.studygroup.domain.Member;
import org.springframework.stereotype.Service;

@Service
public interface CreateGroupMemberPlanService {

  void create(CreatePlanForm createPlanForm, Member member, String groupName);
}
