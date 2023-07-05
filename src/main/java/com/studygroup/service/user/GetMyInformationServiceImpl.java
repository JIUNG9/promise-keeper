package com.studygroup.service.user;

import com.studygroup.dto.member.UserInfoDto;
import com.studygroup.domain.Member;
import com.studygroup.domain.StudyGroupMember;
import com.studygroup.repository.GroupMemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMyInformationServiceImpl implements GetMyInformationService {

  private final GroupMemberRepository groupMemberRepository;

  @Override
  public UserInfoDto getMyInfo(Member member) {
    List<StudyGroupMember> studyGroupMemberList = groupMemberRepository.findByMember(member);

    List<String> groupNameList =
        studyGroupMemberList.
            stream().
            map(list -> list.getStudyGroup().getName()).
            collect(Collectors.toList());

    List<String> groupNickNameList =
        studyGroupMemberList.
            stream().
            map(list -> list.getNickName()).
            collect(Collectors.toList());

    UserInfoDto userInfoDto = UserInfoDto.
        builder().
        groupName(groupNameList).
        groupNickName(groupNickNameList).
        name(member.getName()).
        gender(member.getGender()).
        email(member.getEmail()).
        age(member.getAge()).
        build();
    return userInfoDto;
  }
}
