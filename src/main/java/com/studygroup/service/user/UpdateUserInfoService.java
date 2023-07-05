package com.studygroup.service.user;

import com.studygroup.domain.Member;
import org.springframework.stereotype.Service;

@Service
public interface UpdateUserInfoService {

  void update(Member member, String content);
}
