package com.studygroup.service.group.member;

import com.studygroup.domain.StudyGroupMember;
import com.studygroup.enums.GroupMemberConsentOrDeny;
import com.studygroup.enums.GroupRole;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HandleGroupMemberApplicationServiceImpl implements
    HandleGroupMemberApplicationService {

  private final GroupMemberRepository groupMemberRepository;

  @Override
  public void handle(StudyGroupMember studyGroupMember,
      GroupMemberConsentOrDeny groupMemberConsentOrDeny) {

    if (groupMemberConsentOrDeny.equals(GroupMemberConsentOrDeny.CONSENT)) {
      StudyGroupMember consentTheMember = StudyGroupMember.
          builder().
          studyGroup(studyGroupMember.getStudyGroup()).
          member(studyGroupMember.getMember()).
          warnCount(studyGroupMember.getWarnCount()).
          nickName(studyGroupMember.getNickName()).
          intro(studyGroupMember.getIntro()).
          id(studyGroupMember.getId()).
          studyGroupMemberPlanList(studyGroupMember.getStudyGroupMemberPlanList()).
          groupRole(GroupRole.GROUP_USER).build();

      groupMemberRepository.save(consentTheMember);
    } else {
      StudyGroupMember denyTheMember = StudyGroupMember.
          builder().
          studyGroup(studyGroupMember.getStudyGroup()).
          member(studyGroupMember.getMember()).
          warnCount(studyGroupMember.getWarnCount()).
          nickName(studyGroupMember.getNickName()).
          intro(studyGroupMember.getIntro()).
          studyGroupMemberPlanList(studyGroupMember.getStudyGroupMemberPlanList()).
          id(studyGroupMember.getId()).
          groupRole(GroupRole.GROUP_DENIED).build();

      groupMemberRepository.save(denyTheMember);
    }
  }

}
