package com.studygroup.service.group.member;

import com.studygroup.domain.StudyGroupMember;
import org.springframework.stereotype.Service;


@Service
public interface GroupMemberRemovalService {

  void remove(StudyGroupMember studyGroupMember);
}
