package com.studygroup.service.email;

import com.studygroup.domain.Member;
import org.springframework.stereotype.Service;

@Service
public interface SendTokenToEmailService {

  void sendTokenToEmail(Member member, String token);
}

