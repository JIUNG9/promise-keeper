package com.studygroup.service.group;

import com.studygroup.dto.CreateGroupForm;
import com.studygroup.entity.StudyGroup;
import org.springframework.stereotype.Service;

@Service
public interface CreateGroupService {
    StudyGroup create(CreateGroupForm createGroupForm);
}
