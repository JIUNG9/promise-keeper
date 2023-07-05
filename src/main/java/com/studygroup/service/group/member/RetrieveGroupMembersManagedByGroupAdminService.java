package com.studygroup.service.group.member;

import com.studygroup.dto.GroupMembersDto;
import com.studygroup.domain.Member;
import com.studygroup.domain.StudyGroup;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RetrieveGroupMembersManagedByGroupAdminService {

  List<GroupMembersDto> get(StudyGroup studyGroup, Member member);
}
