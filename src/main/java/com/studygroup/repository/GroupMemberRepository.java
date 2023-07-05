package com.studygroup.repository;


import com.studygroup.domain.Member;
import com.studygroup.domain.StudyGroup;
import com.studygroup.domain.StudyGroupMember;
import com.studygroup.enums.GroupRole;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberRepository extends JpaRepository<StudyGroupMember, Long> {

  int countByStudyGroup(StudyGroup studyGroup);

  Optional<StudyGroupMember> findByStudyGroup_NameAndNickName(String groupName, String nickName);

  List<StudyGroupMember> findByMemberAndGroupRole(Member member, GroupRole groupRole);

  List<StudyGroupMember> findByMember(Member member);

  StudyGroupMember findByStudyGroup_NameAndGroupRole(String groupName, GroupRole groupRole);

  List<StudyGroupMember> findByStudyGroup(StudyGroup studyGroup);

  StudyGroupMember findByStudyGroupAndNickName(StudyGroup studyGroup, String nickName);

  StudyGroupMember findByStudyGroupAndMember_Id(StudyGroup studyGroup, Long memberId);

  StudyGroupMember findByStudyGroup_NameAndMember(String groupName, Member member);


}

