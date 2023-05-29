package com.studygroup.repository;


import com.studygroup.entity.Member;
import com.studygroup.entity.StudyGroup;
import com.studygroup.entity.StudyGroupMember;
import com.studygroup.enums.GroupRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMemberRepository extends JpaRepository<StudyGroupMember, Long> {

    int countByStudyGroup(StudyGroup studyGroup);

    StudyGroupMember findByStudyGroupAndGroupRole(StudyGroup studyGroup, GroupRole groupRole);
    List<StudyGroupMember> findByMember(Member member);
    StudyGroupMember findByStudyGroup_NameAndGroupRole(String groupName,GroupRole groupRole);
    List<StudyGroupMember> findByStudyGroup(StudyGroup studyGroup);
    StudyGroupMember findByStudyGroupAndNickName(StudyGroup studyGroup, String nickName);
    StudyGroupMember findByStudyGroupAndMember_Id(StudyGroup studyGroup,Long memberId);
}

