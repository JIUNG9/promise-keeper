package com.studygroup.service.group;

import com.studygroup.entity.StudyGroup;
import org.springframework.stereotype.Service;

@Service
public interface UpdateGroupIntroService {
    public void update(StudyGroup studyGroup, String intro);
}
