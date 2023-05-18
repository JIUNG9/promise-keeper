package com.studygroup.service.groupmeeting;

import com.studygroup.entity.GroupMeeting;
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
