package com.studygroup.service.groupmember;

import com.studygroup.entity.Member;
import com.studygroup.entity.StudyGroup;
import com.studygroup.entity.StudyGroupMember;
import com.studygroup.enums.GroupRole;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("ApplyTheGroupServiceAsMember")
public class ApplyTheGroupServiceAsMember implements ApplyTheGroupService {
    private final GroupMemberRepository groupMemberRepository;

    @Override
    public void apply(Member member, StudyGroup studyGroup, String nickName, String userIntro) {

        StudyGroupMember newMember = StudyGroupMember.
                builder().
                groupRole(GroupRole.GROUP_PENDING).
                intro(userIntro).
                nickName(nickName).
                warnCount(0).
                member(member).
                studyGroup(studyGroup).
                build();

        groupMemberRepository.save(newMember);

    }
    }