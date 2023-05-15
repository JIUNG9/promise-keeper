package com.studygroup.service.group;

import com.studygroup.entity.StudyGroup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RetrieveAllGroupsService {
    public List<StudyGroup> getAll();
}
