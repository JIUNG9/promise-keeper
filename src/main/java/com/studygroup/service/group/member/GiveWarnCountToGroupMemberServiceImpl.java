package com.studygroup.service.group.member;

import com.studygroup.domain.StudyGroupMember;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiveWarnCountToGroupMemberServiceImpl implements GiveWarnCountToGroupMemberService {

  private final GroupMemberRepository groupMemberRepository;

  @Override
  public void giveCountBy1(String groupName, String nickName) {
    StudyGroupMember studyGroupMember =
        groupMemberRepository.
            findByStudyGroup_NameAndNickName(groupName, nickName).get();

    groupMemberRepository.save(this.getWarnCountIncreasedMember(studyGroupMember));

  }

  public StudyGroupMember getWarnCountIncreasedMember(StudyGroupMember studyGroupMember) {
    return StudyGroupMember.
        builder().
        id(studyGroupMember.getId()).
        studyGroupMemberPlanList(studyGroupMember.getStudyGroupMemberPlanList()).
        studyGroup(studyGroupMember.getStudyGroup()).
        member(studyGroupMember.getMember()).
        warnCount(studyGroupMember.getWarnCount() + 1).
        nickName(studyGroupMember.getNickName()).
        intro(studyGroupMember.getIntro()).
        groupRole(studyGroupMember.getGroupRole()).
        build();
  }
}
