package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroup;
import com.studygroup.entity.StudyGroupMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
public interface GroupMemberRemovalService {
    public void remove(StudyGroupMember studyGroupMember);
}
