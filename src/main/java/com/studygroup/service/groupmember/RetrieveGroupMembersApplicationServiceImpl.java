package com.studygroup.service.groupmember;

import com.studygroup.dto.GroupApplicationList;
import com.studygroup.entity.StudyGroup;
import com.studygroup.entity.StudyGroupMember;
import com.studygroup.enums.GroupRole;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("RetrieveGroupMembersApplicationServiceImpl")
@RequiredArgsConstructor
public class RetrieveGroupMembersApplicationServiceImpl implements RetrieveGroupMembersApplicationService {
    private final GroupMemberRepository groupMemberRepository;

    @Override
    public List<GroupApplicationList> getApplications(StudyGroup studyGroup) {

        return groupMemberRepository.
                findByStudyGroup(studyGroup).
                stream().
                filter(s->s.getGroupRole().equals(GroupRole.GROUP_PENDING)).
                map(s->GroupApplicationList.
                        builder().
                        intro(s.getIntro()).
                        nickName(s.getNickName()).
                        build()).
                collect(Collectors.toList());
    }
}
