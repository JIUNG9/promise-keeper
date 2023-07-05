package com.studygroup.service.group;

import com.studygroup.domain.Member;
import com.studygroup.dto.GroupInfoDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RetrieveGroupsByMangedByGroupAdmin {

  List<GroupInfoDto> get(Member adminMember);
}
