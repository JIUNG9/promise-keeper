package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroupMember;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("GroupMemberIsOutMemberSelfService")
@RequiredArgsConstructor
public class GroupMemberIsOutMemberSelfService implements GroupMemberRemovalService {
    private final GroupMemberRepository groupMemberRepository;
    @Override
    public void remove(StudyGroupMember studyGroupMember) {
        groupMemberRepository.delete(studyGroupMember);

    }
}
