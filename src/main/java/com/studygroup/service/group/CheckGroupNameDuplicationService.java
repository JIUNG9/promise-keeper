package com.studygroup.service.group;

import com.studygroup.repository.GroupRepository;
import com.studygroup.service.CheckDuplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@RequiredArgsConstructor
@Service("CheckGroupNameDuplicationService")
public class CheckGroupNameDuplicationService implements CheckDuplicationService {

    private final GroupRepository groupRepository;
    @Override
    public boolean isDuplicated(String groupName) {
        return Optional.ofNullable(groupRepository.findByName(groupName)).isPresent();
    }
}
