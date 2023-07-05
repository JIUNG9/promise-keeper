package com.studygroup.service.group.member;

import com.studygroup.domain.StudyGroupMember;
import com.studygroup.enums.GroupRole;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckGroupMemberIsAlreadyDeniedImpl implements CheckGroupMemberIsAlreadyDenied {

  private final GroupMemberRepository groupMemberRepository;

  @Override
  public boolean isAlreadyDenied(StudyGroupMember studyGroupMember) {

    return studyGroupMember.getGroupRole().equals(GroupRole.GROUP_DENIED);

  }
}
