package com.studygroup.service.group.member;

import com.studygroup.dto.GroupMembersDto;
import com.studygroup.domain.Member;
import com.studygroup.domain.StudyGroup;
import com.studygroup.enums.GroupRole;
import com.studygroup.repository.GroupMemberRepository;
import com.studygroup.dto.GroupMemberEntityToMembersDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveGroupMembersManagedByGroupAdminServiceImpl implements
    RetrieveGroupMembersManagedByGroupAdminService {

  private final GroupMemberRepository groupMemberRepository;

  @Override
  public List<GroupMembersDto> get(StudyGroup studyGroup, Member member) {

    return groupMemberRepository.
        findByStudyGroup(studyGroup).
        stream().
        filter(admin -> admin.getGroupRole() != GroupRole.GROUP_ADMIN).
        map(studyGroupMember -> GroupMemberEntityToMembersDto.convert(studyGroupMember)).
        collect(Collectors.toList());

  }
}
