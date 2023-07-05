package com.studygroup.repository;


import com.studygroup.domain.StudyGroup;
import com.studygroup.enums.MainCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<StudyGroup, Integer> {

  StudyGroup findByName(String groupName
  );

  List<StudyGroup> findBySubjectLike(String subject);

  List<StudyGroup> findByMainCategory(MainCategory mainCategory);

}
