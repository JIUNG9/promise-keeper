package com.studygroup.service.group;

import com.studygroup.entity.StudyGroup;
import com.studygroup.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateGroupIntroServiceImpl implements UpdateGroupIntroService {

    private final GroupRepository groupRepository;

    @Override
    public void update(StudyGroup group, String intro) {
        StudyGroup infoUpdatedGroup =
                StudyGroup.
                        builder().
                        id(group.getId()).
                        name(group.getName()).
                        mainCategory(group.getMainCategory()).
                        info(intro).
                        subject(group.getSubject()).
                        studyGroupMemberList(group.getStudyGroupMemberList()).
                        groupMeetingList(group.getGroupMeetingList()).
                        build();

        groupRepository.save(infoUpdatedGroup);

    }
}
