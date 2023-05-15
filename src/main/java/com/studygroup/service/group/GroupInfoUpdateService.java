package com.studygroup.service.group;

import com.studygroup.entity.StudyGroup;
import com.studygroup.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GroupInfoUpdateService implements GroupUpdateInfoService {

    private final GroupRepository groupRepository;

    @Override
    public void update(StudyGroup group, String newInfo) {
        StudyGroup nameUpdatedGroup =
                StudyGroup.
                        builder().
                        id(group.getId()).
                        name(group.getName()).
                        mainCategory(group.getMainCategory()).
                        info(newInfo).
                        subject(group.getSubject()).
                        build();

        groupRepository.save(nameUpdatedGroup);
    }
}
