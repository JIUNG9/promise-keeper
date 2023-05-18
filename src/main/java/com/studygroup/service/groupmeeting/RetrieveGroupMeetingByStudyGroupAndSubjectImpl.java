package com.studygroup.service.groupmeeting;

import com.studygroup.entity.GroupMeeting;
import com.studygroup.entity.StudyGroup;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.GroupMeetingRepository;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service("RetrieveGroupMeetingByStudyGroupAndSubjectImpl")
public class RetrieveGroupMeetingByStudyGroupAndSubjectImpl implements RetrieveGroupMeetingByStudyGroupAndSubject {

    private final GroupMeetingRepository groupMeetingRepository;


    @Override
    public GroupMeeting get(StudyGroup studyGroup, String subject) {
    return  Optional.
                ofNullable(groupMeetingRepository.findByStudyGroupAndSubject(studyGroup,subject)).
                orElseThrow(BindParameterSupplier.
                        bind(CustomIllegalArgumentException::new,
                                ErrorCode.GROUP_MEETING_SUBJECT_IS_NOT_EXISTED));
    }
}
