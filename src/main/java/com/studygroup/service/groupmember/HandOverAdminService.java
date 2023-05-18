package com.studygroup.service.groupmember;

import com.studygroup.repository.GroupMemberRepository;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.BindException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HandOverAdminService implements SetGroupAdminService {

    private final GroupMemberRepository groupMemberRepository;
    @Override
    public void setAdmin(String groupName, String userNickName) {

    }
}
