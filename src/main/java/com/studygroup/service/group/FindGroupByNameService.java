package com.studygroup.service.group;

import com.studygroup.domain.StudyGroup;
import com.studygroup.exception.CustomIllegalArgumentException;
import com.studygroup.repository.GroupRepository;
import com.studygroup.util.error.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindGroupByNameService implements FindGroupService {

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
