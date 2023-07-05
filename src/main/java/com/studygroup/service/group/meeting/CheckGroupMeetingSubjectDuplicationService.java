package com.studygroup.service.group.meeting;

import com.studygroup.repository.GroupMeetingRepository;
import com.studygroup.service.common.CheckDuplicationService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("CheckGroupMeetingSubjectDuplicationService")
@RequiredArgsConstructor
public class CheckGroupMeetingSubjectDuplicationService implements CheckDuplicationService<String> {

  private final GroupMeetingRepository groupMeetingRepository;

  @Override
  public boolean isDuplicated(String subject) {
    return Optional.ofNullable(groupMeetingRepository.findBySubject(subject)).isPresent();
  }
}
