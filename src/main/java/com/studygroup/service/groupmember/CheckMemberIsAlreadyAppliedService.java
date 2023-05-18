package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroup;
import org.springframework.stereotype.Service;

@Service
public interface CheckMemberIsAlreadyAppliedService {
    public boolean isAlreadyApplied(StudyGroup studyGroup , Long memberId);
}
