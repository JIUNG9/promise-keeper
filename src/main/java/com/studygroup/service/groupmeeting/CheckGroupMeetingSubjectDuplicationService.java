package com.studygroup.service.groupmeeting;

import com.studygroup.repository.GroupMeetingRepository;
import com.studygroup.service.CheckDuplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("CheckGroupMeetingSubjectDuplicationService")
@RequiredArgsConstructor
public class CheckGroupMeetingSubjectDuplicationService implements CheckDuplicationService {
    private final GroupMeetingRepository groupMeetingRepository;
    @Override
    public boolean isDuplicated(String subject) {
        return Optional.ofNullable(groupMeetingRepository.findBySubject(subject)).isPresent();
    }
}
