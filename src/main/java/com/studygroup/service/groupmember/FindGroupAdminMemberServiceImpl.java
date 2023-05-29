package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroupMember;
import com.studygroup.enums.GroupRole;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("FindGroupAdminMemberServiceImpl")
@RequiredArgsConstructor
public class FindGroupAdminMemberServiceImpl implements FindGroupAdminMemberService {
    private GroupMemberRepository groupMemberRepository;
    @Override
    public StudyGroupMember findAdminMember(String groupName) {

        return groupMemberRepository.
                findByStudyGroup_NameAndGroupRole(
                        groupName,
                        GroupRole.GROUP_ADMIN);

    }
}
