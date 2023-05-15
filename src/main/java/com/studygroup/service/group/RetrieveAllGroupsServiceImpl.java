package com.studygroup.service.group;

import com.studygroup.entity.StudyGroup;
import com.studygroup.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrieveAllGroupsServiceImpl implements RetrieveAllGroupsService {

    private final GroupRepository groupRepository;

    @Override
    public List<StudyGroup> getAll() {
        return groupRepository.findAll();
    }
}
