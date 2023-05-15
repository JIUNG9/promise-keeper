package com.studygroup.service.group;

import com.studygroup.entity.StudyGroup;
import com.studygroup.entity.StudyGroupMember;
import com.studygroup.enums.MainCategory;
import com.studygroup.repository.GroupMemberRepository;
import com.studygroup.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service("DeleteGroupServiceAsGroupAdmin")
@RequiredArgsConstructor
public class DeleteGroupServiceAsGroupAdmin implements DeleteGroupService {
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    @Override
    public void delete(StudyGroup studyGroup) {
        StudyGroup deletedStudyGroup = StudyGroup.builder().info(studyGroup.getInfo()).mainCategory(studyGroup.getMainCategory()).subject(studyGroup.getSubject()).name(studyGroup.getName()).id(studyGroup.getId()).build();
//        groupRepository.save(deletedStudyGroup);
//        groupRepository.delete(deletedStudyGroup);
//        groupMemberRepository.deleteAll(studyGroup.getStudyGroupMemberList());
//        groupMemberRepository.delete(studyGroup.getStudyGroupMemberList().get(0));

        groupRepository.delete(studyGroup);

    }
}
