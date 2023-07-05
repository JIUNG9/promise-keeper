package com.studygroup.service.group.member;

import com.studygroup.domain.StudyGroup;
import com.studygroup.domain.StudyGroupMember;
import com.studygroup.exception.GroupMemberAuthorizationException;
import com.studygroup.repository.GroupMemberRepository;
import com.studygroup.util.convertor.ObjectToLong;
import com.studygroup.util.error.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("FindGroupMemberByIdService")
@RequiredArgsConstructor
public class FindGroupMemberByIdService implements FindGroupMemberService {

  private final GroupMemberRepository groupMemberRepository;

  @Override
  public StudyGroupMember getGroupMember(StudyGroup studyGroup, Object memberId) {
    return Optional.
        ofNullable(groupMemberRepository.findByStudyGroupAndMember_Id(studyGroup,
            ObjectToLong.convert(memberId))).
        orElseThrow(BindParameterSupplier.
            bind(GroupMemberAuthorizationException::new,
                ErrorCode.YOU_ARE_NOT_GROUP_MEMBER.getMessage()));
  }
}
