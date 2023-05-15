package com.studygroup.service.group;

import com.studygroup.entity.StudyGroup;
import com.studygroup.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrieveGroupsBySubjectServiceImpl implements RetrieveGroupsBySubjectService {

    private final GroupRepository groupRepository;
    @Override
    public List<StudyGroup> find(String subject) {
        return groupRepository.findBySubjectLike("%" + subject + "%");
    }
}
