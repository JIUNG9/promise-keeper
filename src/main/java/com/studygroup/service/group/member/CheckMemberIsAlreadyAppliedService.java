package com.studygroup.service.group.member;

import com.studygroup.domain.StudyGroup;
import org.springframework.stereotype.Service;

@Service
public interface CheckMemberIsAlreadyAppliedService {

  boolean isAlreadyApplied(StudyGroup studyGroup, Long memberId);
}
