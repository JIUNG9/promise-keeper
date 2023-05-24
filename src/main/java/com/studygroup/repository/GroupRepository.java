package com.studygroup.repository;


import com.studygroup.entity.Member;
import com.studygroup.entity.StudyGroup;
import com.studygroup.entity.StudyGroupMember;
import com.studygroup.enums.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<StudyGroup, Integer> {
     StudyGroup findByName(String name);
     List<StudyGroup> findBySubjectLike(String subject);
     List<StudyGroup> findByMainCategory(MainCategory mainCategory);

}
