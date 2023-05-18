package com.studygroup.service.user;

import com.studygroup.entity.Member;
import org.springframework.stereotype.Service;

@Service
public interface UpdateUserIsEnableService {
    public void update(Member member, boolean isEnabled);
}
