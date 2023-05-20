package com.studygroup.service.groupmember;

import com.studygroup.entity.StudyGroup;
import com.studygroup.entity.StudyGroupMember;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.GroupMemberRepository;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("FindGroupMemberByNickNameService")
@RequiredArgsConstructor
public class FindGroupMemberByNickNameService implements FindGroupMemberService {
    private final GroupMemberRepository groupMemberRepository;

    @Override
    public StudyGroupMember getGroupMember(StudyGroup studyGroup, Object nickName) {
        String memberNickName = nickName.toString();
        return Optional.
                ofNullable(groupMemberRepository.
                        findByStudyGroupAndNickName(studyGroup, memberNickName)).
                orElseThrow(BindParameterSupplier.
                        bind(CustomIllegalArgumentException::new,
                                ErrorCode.YOU_ARE_NOT_GROUP_MEMBER));
    }
}
