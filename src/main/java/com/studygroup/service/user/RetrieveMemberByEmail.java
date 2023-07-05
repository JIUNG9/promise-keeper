package com.studygroup.service.user;

import com.studygroup.domain.Member;
import org.springframework.stereotype.Service;

@Service
public interface RetrieveMemberByEmail {

  Member getMember(String email);
}
