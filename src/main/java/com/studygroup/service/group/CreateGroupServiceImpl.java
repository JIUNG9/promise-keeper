package com.studygroup.service.group;

import com.studygroup.dto.CreateGroupForm;
import com.studygroup.domain.StudyGroup;
import com.studygroup.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("CreateGroupServiceImpl")
public class CreateGroupServiceImpl implements CreateGroupService {

  private final GroupRepository groupRepository;

  @Override
  public StudyGroup create(CreateGroupForm createGroupForm) {

    StudyGroup newStudyGroup = StudyGroup.
        builder().
        mainCategory(createGroupForm.getMainCategory()).
        subject(createGroupForm.getSubject()).
        name(createGroupForm.getName()).
        info(createGroupForm.getInfo()).
        build();

    return groupRepository.save(newStudyGroup);


  }
}
