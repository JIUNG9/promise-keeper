package com.studygroup.service.group.plan;

import com.studygroup.dto.CreatePlanForm;
import com.studygroup.domain.Member;
import com.studygroup.domain.StudyGroupMember;
import com.studygroup.domain.StudyGroupMemberPlan;
import com.studygroup.repository.GroupMemberPlanRepository;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateGroupMemberPlanServiceImpl implements CreateGroupMemberPlanService {

  private final GroupMemberPlanRepository groupMemberPlanRepository;
  private final GroupMemberRepository groupMemberRepository;

  @Override
  public void create(CreatePlanForm createPlanForm, Member member, String groupName) {

    StudyGroupMember studyGroupMember =
        groupMemberRepository.
            findByStudyGroup_NameAndMember(groupName, member);

    StudyGroupMemberPlan studyGroupMemberPlan =
        StudyGroupMemberPlan.
            builder().
            title(createPlanForm.getTitle()).
            startDate(createPlanForm.getStartDate()).
            endDate(createPlanForm.getEndDate()).
            startTime(createPlanForm.getStartTime()).
            endTime(createPlanForm.getEndTime()).
            payload(createPlanForm.getPayload()).
            studyGroupMember(studyGroupMember).
            build();
    groupMemberPlanRepository.save(studyGroupMemberPlan);

  }
}
