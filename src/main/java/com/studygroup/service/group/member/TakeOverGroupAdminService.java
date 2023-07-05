package com.studygroup.service.group.member;

import com.studygroup.domain.StudyGroupMember;
import org.springframework.stereotype.Service;

@Service
public interface TakeOverGroupAdminService {

  void takeOver(StudyGroupMember oldGroupAdmin, StudyGroupMember newGroupAdmin);
}
