package com.studygroup.service.user;

import com.studygroup.dto.MyInfoDto;
import com.studygroup.entity.Member;
import com.studygroup.entity.StudyGroupMember;
import com.studygroup.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetMyInformationServiceImpl implements GetMyInformationService {
    private final GroupMemberRepository groupMemberRepository;
    @Override
    public MyInfoDto getMyInfo(Member member) {
        List<StudyGroupMember> studyGroupMemberList = groupMemberRepository.findByMember(member);

        List<String> groupNameList =
                studyGroupMemberList.
                        stream().
                        map(list -> list.getStudyGroup().getName()).
                        collect(Collectors.toList());

        MyInfoDto myInfoDto =MyInfoDto.
                                builder().
                                groupName(groupNameList).
                                name(member.getName()).
                                gender(member.getGender()).
                                email(member.getEmail()).
                                age(member.getAge()).
                                build();
      return myInfoDto;
    }
}
