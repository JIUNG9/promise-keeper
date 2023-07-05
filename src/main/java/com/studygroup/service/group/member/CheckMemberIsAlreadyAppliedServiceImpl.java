package com.studygroup.service.group.member;

import com.studygroup.domain.StudyGroup;
import com.studygroup.repository.GroupMemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckMemberIsAlreadyAppliedServiceImpl implements CheckMemberIsAlreadyAppliedService {

  private final GroupMemberRepository groupMemberRepository;

  @Override
  public boolean isAlreadyApplied(StudyGroup studyGroup, Long memberId) {

    return Optional.
        ofNullable(groupMemberRepository.
            findByStudyGroupAndMember_Id(studyGroup, memberId)).isPresent();
  }
}
