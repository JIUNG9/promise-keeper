package com.studygroup.service.group;

import com.studygroup.dto.CreateGroupForm;
import com.studygroup.entity.StudyGroup;
import com.studygroup.repository.GroupRepository;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service("CreateGroupServiceImpl")
public class CreateGroupServiceImpl implements CreateGroupService {

    private final GroupRepository groupRepository;
    @Override
    public StudyGroup create(CreateGroupForm createGroupForm) {

        StudyGroup newStudyGroup =StudyGroup.
                                    builder().
                                    mainCategory(createGroupForm.getMainCategory()).
                                    subject(createGroupForm.getSubject()).
                                    name(createGroupForm.getName()).
                                    info(createGroupForm.getInfo()).
                                    build();


                return groupRepository.save(newStudyGroup);




    }
}
