package com.studygroup.service.group;

import com.studygroup.entity.StudyGroup;
import com.studygroup.enums.MainCategory;
import com.studygroup.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RetrieveGroupsByCategoryServiceImpl")
@RequiredArgsConstructor
public class RetrieveGroupsByCategoryServiceImpl implements RetrieveGroupsByCategoryService {

    private final GroupRepository groupRepository;
    @Override
    public List<StudyGroup> get(MainCategory mainCategory) {
        return groupRepository.findByMainCategory(mainCategory);
    }
}
