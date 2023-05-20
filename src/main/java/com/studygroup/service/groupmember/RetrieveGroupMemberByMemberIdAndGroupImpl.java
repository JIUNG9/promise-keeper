package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroup;
import com.studygroup.entity.StudyGroupMember;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.exception.GroupMemberAuthorizationException;
import com.studygroup.repository.GroupMemberRepository;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RetrieveGroupMemberByMemberIdAndGroupImpl implements RetrieveGroupMemberByMemberIdAndGroup {
    private final GroupMemberRepository groupMemberRepository;
    @Override
    public StudyGroupMember get(StudyGroup studyGroup, Long memberId) {
        return Optional.
                ofNullable(groupMemberRepository.findByStudyGroupAndMember_Id(studyGroup, memberId)).
                orElseThrow(BindParameterSupplier.
                        bind(GroupMemberAuthorizationException::new, ErrorCode.YOU_ARE_NOT_GROUP_MEMBER.getMessage()));    }
}
