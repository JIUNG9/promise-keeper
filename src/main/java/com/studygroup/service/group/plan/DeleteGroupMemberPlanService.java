package com.studygroup.service.group.plan;

import com.studygroup.domain.Member;
import org.springframework.stereotype.Service;

@Service
public interface DeleteGroupMemberPlanService {

  void delete(Long planId, Member member, String studyGroup);
}
