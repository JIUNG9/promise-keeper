package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroup;
import com.studygroup.entity.StudyGroupMember;
import com.studygroup.exception.GroupMemberAuthorizationException;
import com.studygroup.repository.GroupMemberRepository;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.constant.ObjectToLong;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("FindGroupMemberByIdService")
@RequiredArgsConstructor
public class FindGroupMemberByIdService implements FindGroupMemberService {
    private final GroupMemberRepository groupMemberRepository;

    @Override
    public StudyGroupMember getGroupMember(StudyGroup studyGroup, Object memberId) {
        return Optional.
                ofNullable(groupMemberRepository.findByStudyGroupAndMember_Id(studyGroup, ObjectToLong.convert(memberId))).
                orElseThrow(BindParameterSupplier.
                        bind(GroupMemberAuthorizationException::new, ErrorCode.YOU_ARE_NOT_GROUP_MEMBER.getMessage()));
    }
}
