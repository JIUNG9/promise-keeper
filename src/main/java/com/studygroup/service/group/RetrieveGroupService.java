package com.studygroup.service.group;

import com.studygroup.entity.StudyGroup;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.GroupRepository;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RetrieveGroupService implements FindGroupService {
    private final GroupRepository groupRepository;
    @Override
    public StudyGroup getGroup(String groupName) {
        return Optional.
                    ofNullable(groupRepository.findByName(groupName)).
                    orElseThrow(BindParameterSupplier.
                        bind(CustomIllegalArgumentException::new,
                        ErrorCode.GROUP_IS_NOT_EXISTED_BY_NAME));
    }
}
