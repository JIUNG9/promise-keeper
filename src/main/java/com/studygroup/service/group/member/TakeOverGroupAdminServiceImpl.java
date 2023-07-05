package com.studygroup.service.group.member;

import com.studygroup.domain.StudyGroupMember;
import com.studygroup.enums.GroupRole;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TakeOverGroupAdminServiceImpl implements TakeOverGroupAdminService {

  private final GroupMemberRepository groupMemberRepository;

  @Transactional
  @Override
  public void takeOver(StudyGroupMember oldGroupAdmin, StudyGroupMember newGroupAdmin) {

    oldGroupAdmin = StudyGroupMember.
        builder().
        groupRole(GroupRole.GROUP_USER).
        id(oldGroupAdmin.getId()).
        intro(oldGroupAdmin.getIntro()).
        nickName(oldGroupAdmin.getNickName()).
        warnCount(oldGroupAdmin.getWarnCount()).
        member(oldGroupAdmin.getMember()).
        studyGroup(oldGroupAdmin.getStudyGroup()).
        studyGroupMemberPlanList(oldGroupAdmin.getStudyGroupMemberPlanList()).
        build();

    newGroupAdmin = StudyGroupMember.
        builder().
        groupRole(GroupRole.GROUP_ADMIN).
        id(newGroupAdmin.getId()).
        intro(newGroupAdmin.getIntro()).
        nickName(newGroupAdmin.getNickName()).
        warnCount(newGroupAdmin.getWarnCount()).
        member(newGroupAdmin.getMember()).
        studyGroup(newGroupAdmin.getStudyGroup()).
        studyGroupMemberPlanList(newGroupAdmin.getStudyGroupMemberPlanList()).
        build();

    groupMemberRepository.save(oldGroupAdmin);
    groupMemberRepository.save(newGroupAdmin);

  }
}
