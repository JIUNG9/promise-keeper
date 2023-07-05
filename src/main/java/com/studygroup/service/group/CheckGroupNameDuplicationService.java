package com.studygroup.service.group;

import com.studygroup.repository.GroupRepository;
import com.studygroup.service.common.CheckDuplicationService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("CheckGroupNameDuplicationService")
public class CheckGroupNameDuplicationService implements CheckDuplicationService<String> {

  private final GroupRepository groupRepository;

  @Override
  public boolean isDuplicated(String groupName) {
    return Optional.ofNullable(groupRepository.findByName(groupName)).isPresent();
  }

}
