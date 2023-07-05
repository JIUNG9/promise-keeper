package com.studygroup.service.group;

import com.studygroup.repository.GroupMeetingRepository;
import com.studygroup.service.common.DuplicationCheckerProvider;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupMeetingSubjectDuplicationCheckerService implements
    DuplicationCheckerProvider<String> {

  private final GroupMeetingRepository groupMeetingRepository;

  @Override
  public boolean isDuplicated(String subject) {
    return Optional.ofNullable(groupMeetingRepository.findBySubject(subject)).isPresent();
  }
}
