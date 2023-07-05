package com.studygroup.service.group;

import com.studygroup.domain.StudyGroup;
import com.studygroup.enums.MainCategory;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RetrieveGroupsByCategoryService {

  List<StudyGroup> get(MainCategory mainCategory);
}
