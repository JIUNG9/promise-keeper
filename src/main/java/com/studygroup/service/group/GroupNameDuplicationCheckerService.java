package com.studygroup.service.group;

import com.studygroup.repository.GroupRepository;
import com.studygroup.service.common.DuplicationCheckerProvider;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupNameDuplicationCheckerService implements DuplicationCheckerProvider<String> {
  private final GroupRepository groupRepository;
  @Override
  public boolean isDuplicated(String groupName) {
    return Optional.ofNullable(groupRepository.findByName(groupName)).isPresent();
  }
}
