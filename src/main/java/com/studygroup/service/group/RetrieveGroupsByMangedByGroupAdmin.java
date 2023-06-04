package com.studygroup.service.group;

import com.studygroup.util.GroupInfoDto;
import com.studygroup.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RetrieveGroupsByMangedByGroupAdmin {
    public List<GroupInfoDto> get(Member adminMember);
}
