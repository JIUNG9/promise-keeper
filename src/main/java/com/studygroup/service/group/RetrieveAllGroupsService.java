package com.studygroup.service.group;

import com.studygroup.domain.StudyGroup;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RetrieveAllGroupsService {

  List<StudyGroup> getAll();
}
