package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroup;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckGroupMemberAlreadyAppliedServiceImpl implements CheckGroupMemberAlreadyAppliedService {
    private final GroupMemberRepository groupMemberRepository;
    @Override
    public boolean isAlreadyApplied(StudyGroup studyGroup, Long memberId) {
        return   Optional.
                    ofNullable(groupMemberRepository.
                            findByStudyGroupAndMember_Id(
                                    studyGroup, memberId)).isPresent();
    }
}
