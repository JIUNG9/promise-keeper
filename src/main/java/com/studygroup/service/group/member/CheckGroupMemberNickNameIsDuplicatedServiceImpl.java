package com.studygroup.service.group.member;

import com.studygroup.domain.StudyGroup;
import com.studygroup.repository.GroupMemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckGroupMemberNickNameIsDuplicatedServiceImpl implements
    CheckGroupMemberNickNameIsDuplicatedService {

  private final GroupMemberRepository groupMemberRepository;

  @Override
  public boolean isDuplicated(StudyGroup studyGroup, String nickName) {
    return Optional.ofNullable(
        groupMemberRepository.findByStudyGroupAndNickName(studyGroup, nickName)).isPresent();
  }
}
