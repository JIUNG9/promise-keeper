package com.studygroup.service.group;

import com.studygroup.domain.Member;
import com.studygroup.enums.GroupRole;
import com.studygroup.repository.GroupMemberRepository;
import com.studygroup.dto.GroupEntityToGroupInfoDto;
import com.studygroup.dto.GroupInfoDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveGroupsByMangedByGroupAdminImpl implements RetrieveGroupsByMangedByGroupAdmin {

  private final GroupMemberRepository groupMemberRepository;

  @Override
  public List<GroupInfoDto> get(Member adminMember) {

    return groupMemberRepository.
        findByMemberAndGroupRole(adminMember, GroupRole.GROUP_ADMIN).
        stream().
        map(groupMember -> GroupEntityToGroupInfoDto.convert(groupMember)).
        collect(Collectors.toList());

  }
}
