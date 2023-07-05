package com.studygroup.service.group.member;

import com.studygroup.dto.GroupApplicationList;
import com.studygroup.domain.StudyGroup;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RetrieveGroupMembersApplicationService {

  List<GroupApplicationList> getApplications(StudyGroup studyGroup);
}
