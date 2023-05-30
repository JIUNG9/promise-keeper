package com.studygroup.service.groupmember;

import com.studygroup.dto.GroupMembersDto;
import com.studygroup.entity.Member;
import com.studygroup.entity.StudyGroup;
import com.studygroup.enums.GroupRole;
import com.studygroup.repository.GroupMemberRepository;
import com.studygroup.util.GroupMemberEntityToMembersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetrieveGroupMembersManagedByGroupAdminServiceImpl implements RetrieveGroupMembersManagedByGroupAdminService {
    private final GroupMemberRepository groupMemberRepository;
    @Override
    public List<GroupMembersDto> get(StudyGroup studyGroup, Member member) {

       return groupMemberRepository.
               findByStudyGroup(studyGroup).
               stream().
               filter(admin -> admin.getGroupRole()!= GroupRole.GROUP_ADMIN).
               map(studyGroupMember -> GroupMemberEntityToMembersDto.convert(studyGroupMember)).
               collect(Collectors.toList());

    }
}
