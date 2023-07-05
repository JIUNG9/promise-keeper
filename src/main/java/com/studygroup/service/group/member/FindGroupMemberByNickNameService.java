package com.studygroup.service.group.member;

import com.studygroup.domain.StudyGroup;
import com.studygroup.domain.StudyGroupMember;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.GroupMemberRepository;
import com.studygroup.util.error.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
