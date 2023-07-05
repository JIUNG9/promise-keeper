package com.studygroup.service.group;

import com.studygroup.domain.StudyGroup;
import com.studygroup.repository.GroupRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveGroupsBySubjectServiceImpl implements RetrieveGroupsBySubjectService {

  private final GroupRepository groupRepository;

  @Override
  public List<StudyGroup> get(String subject) {
    return groupRepository.findBySubjectLike("%" + subject + "%");
  }
}
