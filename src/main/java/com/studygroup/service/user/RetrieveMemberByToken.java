package com.studygroup.service.user;

import com.studygroup.entity.Member;
import org.springframework.stereotype.Service;

@Service
public interface RetrieveMemberByToken {
    public Member getMember(String token);
}
