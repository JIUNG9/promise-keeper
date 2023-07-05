package com.studygroup.service.user;

import com.studygroup.domain.Member;
import org.springframework.stereotype.Service;

@Service
public interface UpdateUserPasswordService {

  void update(Member member, String password);
}
