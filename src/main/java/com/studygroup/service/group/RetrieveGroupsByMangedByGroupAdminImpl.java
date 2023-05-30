package com.studygroup.service.group;

import com.studygroup.enums.GroupRole;
import com.studygroup.util.GroupInfoDto;
import com.studygroup.entity.Member;
import com.studygroup.repository.GroupMemberRepository;
import com.studygroup.util.GroupEntityToGroupInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
