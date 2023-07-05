package com.studygroup.service.group.meeting;

import com.studygroup.domain.GroupMeeting;
import com.studygroup.repository.GroupMeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteGroupMeetingServiceImpl implements DeleteGroupMeetingService {

  private final GroupMeetingRepository groupMeetingRepository;

  @Override
  public void delete(GroupMeeting groupMeeting) {
    groupMeetingRepository.delete(groupMeeting);
  }
}
