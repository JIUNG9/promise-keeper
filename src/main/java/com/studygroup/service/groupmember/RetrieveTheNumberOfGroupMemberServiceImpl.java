package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroup;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveTheNumberOfGroupMemberServiceImpl implements RetrieveTheNumberOfGroupMemberService {

    private final GroupMemberRepository groupMemberRepository;
    @Override
    public int getTheNumberOfGroupMember(StudyGroup studyGroup) {

        return groupMemberRepository.countByStudyGroup(studyGroup);
    }
}
