package com.studygroup.service.group;

import com.studygroup.domain.StudyGroup;
import com.studygroup.enums.MainCategory;
import com.studygroup.repository.GroupRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("RetrieveGroupsByCategoryServiceImpl")
@RequiredArgsConstructor
public class RetrieveGroupsByCategoryServiceImpl implements RetrieveGroupsByCategoryService {

  private final GroupRepository groupRepository;

  @Override
  public List<StudyGroup> get(MainCategory mainCategory) {
    return groupRepository.findByMainCategory(mainCategory);
  }
}
