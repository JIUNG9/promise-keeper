package com.studygroup.service.group.member;

import com.studygroup.domain.StudyGroupMember;
import com.studygroup.enums.GroupMemberConsentOrDeny;
import org.springframework.stereotype.Service;

@Service
public interface HandleGroupMemberApplicationService {

  void handle(StudyGroupMember studyGroupMember,
      GroupMemberConsentOrDeny groupMemberConsentOrDeny);
}
