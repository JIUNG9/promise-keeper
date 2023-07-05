package com.studygroup.service.group;

import com.studygroup.domain.StudyGroup;
import org.springframework.stereotype.Service;

@Service
public interface UpdateGroupIntroService {

  void update(StudyGroup studyGroup, String intro);
}
