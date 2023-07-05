package com.studygroup.service.group;

import com.studygroup.domain.StudyGroup;
import com.studygroup.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("UpdateGroupNameService")
public class UpdateGroupNameService implements GroupUpdateNameService {

  private final GroupRepository groupRepository;

  @Override
  public void update(StudyGroup group, String newGroupName) {

    StudyGroup nameUpdatedGroup =
        StudyGroup.
            builder().
            id(group.getId()).
            name(newGroupName).
            mainCategory(group.getMainCategory()).
            info(group.getInfo()).
            subject(group.getSubject()).
            studyGroupMemberList(group.getStudyGroupMemberList()).
            groupMeetingList(group.getGroupMeetingList()).
            build();

    groupRepository.save(nameUpdatedGroup);
  }
}
