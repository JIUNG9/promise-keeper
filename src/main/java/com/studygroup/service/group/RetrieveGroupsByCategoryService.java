package com.studygroup.service.group;

import com.studygroup.entity.StudyGroup;
import com.studygroup.enums.MainCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RetrieveGroupsByCategoryService {
    public List<StudyGroup> get(MainCategory mainCategory);
}
