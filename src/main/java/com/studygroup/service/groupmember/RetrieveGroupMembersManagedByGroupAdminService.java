package com.studygroup.service.groupmember;

import com.studygroup.dto.GroupMembersDto;
import com.studygroup.entity.Member;
import com.studygroup.entity.StudyGroup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RetrieveGroupMembersManagedByGroupAdminService {
    public List<GroupMembersDto> get(StudyGroup studyGroup, Member member);
}
