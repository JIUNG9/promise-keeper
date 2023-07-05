package com.studygroup.service.group.plan;

import com.studygroup.domain.Member;
import com.studygroup.domain.StudyGroupMember;
import com.studygroup.domain.StudyGroupMemberPlan;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.GroupMemberPlanRepository;
import com.studygroup.repository.GroupMemberRepository;
import com.studygroup.util.error.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteGroupMemberPlanServiceImpl implements DeleteGroupMemberPlanService {

  private final GroupMemberPlanRepository groupMemberPlanRepository;
  private final GroupMemberRepository groupMemberRepository;

  @Override
  public void delete(Long planId, Member member, String groupName) {

    StudyGroupMember studyGroupMember =
        groupMemberRepository.
            findByStudyGroup_NameAndMember(groupName, member);

    StudyGroupMemberPlan studyGroupMemberPlan =
        groupMemberPlanRepository.
            findByStudyGroupMemberAndId(studyGroupMember, planId).
            orElseThrow(BindParameterSupplier.bind(
                CustomIllegalArgumentException::new,
                ErrorCode.PLAN_IS_NOT_EXIST));

    groupMemberPlanRepository.delete(studyGroupMemberPlan);
  }
}
