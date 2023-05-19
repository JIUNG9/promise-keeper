package com.studygroup.repository;


import com.studygroup.entity.Member;
import com.studygroup.entity.StudyGroup;
import com.studygroup.entity.StudyGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMemberRepository extends JpaRepository<StudyGroupMember, Integer> {

    int countByStudyGroup(StudyGroup studyGroup);
    List<StudyGroupMember> findByStudyGroup(StudyGroup studyGroup);
    StudyGroupMember findByStudyGroupAndNickName(StudyGroup studyGroup, String nickName);
    StudyGroupMember findByStudyGroupAndMember_Id(StudyGroup studyGroup,Long memberId);
}

