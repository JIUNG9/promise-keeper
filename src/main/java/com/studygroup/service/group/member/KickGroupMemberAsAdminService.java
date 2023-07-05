package com.studygroup.service.group.member;

import com.studygroup.domain.StudyGroupMember;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("KickGroupMemberAsAdminService")
@RequiredArgsConstructor
public class KickGroupMemberAsAdminService implements GroupMemberRemovalService {

  private final GroupMemberRepository groupMemberRepository;

  @Override
  public void remove(StudyGroupMember studyGroupMember) {
    groupMemberRepository.delete(studyGroupMember);
  }
}
