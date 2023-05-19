package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroup;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("TheNumberOfGroupMembersService")
@RequiredArgsConstructor
public class TheNumberOfGroupMembersService implements GroupMemberStaticsService {

    private final GroupMemberRepository groupMemberRepository;

    @Override
    public int getStatics(StudyGroup studyGroup) {
        return groupMemberRepository.countByStudyGroup(studyGroup);
    }
}
