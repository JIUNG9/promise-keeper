package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroup;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckGroupMemberNickNameIsDuplicatedServiceImpl implements CheckGroupMemberNickNameIsDuplicatedService {

    private final GroupMemberRepository groupMemberRepository;

    @Override
    public boolean isDuplicated(StudyGroup studyGroup, String nickName) {
        return Optional.ofNullable(groupMemberRepository.findByStudyGroupAndNickName(studyGroup, nickName)).isPresent();
    }
}
