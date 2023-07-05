package com.studygroup.service.group;

import com.studygroup.dto.CreateGroupForm;
import com.studygroup.domain.StudyGroup;
import org.springframework.stereotype.Service;

@Service
public interface CreateGroupService {

  StudyGroup create(CreateGroupForm createGroupForm);
}
