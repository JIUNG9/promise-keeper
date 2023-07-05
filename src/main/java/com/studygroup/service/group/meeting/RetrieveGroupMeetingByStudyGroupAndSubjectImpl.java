package com.studygroup.service.group.meeting;

import com.studygroup.domain.GroupMeeting;
import com.studygroup.domain.StudyGroup;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.GroupMeetingRepository;
import com.studygroup.util.error.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("RetrieveGroupMeetingByStudyGroupAndSubjectImpl")
public class RetrieveGroupMeetingByStudyGroupAndSubjectImpl implements
    RetrieveGroupMeetingByStudyGroupAndSubject {

  private final GroupMeetingRepository groupMeetingRepository;


  @Override
  public GroupMeeting get(StudyGroup studyGroup, String subject) {
    return Optional.
        ofNullable(groupMeetingRepository.findByStudyGroupAndSubject(studyGroup, subject)).
        orElseThrow(BindParameterSupplier.
            bind(CustomIllegalArgumentException::new,
                ErrorCode.GROUP_MEETING_SUBJECT_IS_NOT_EXISTED));
  }
}
