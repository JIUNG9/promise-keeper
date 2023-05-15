package com.studygroup.service.email;

import com.studygroup.entity.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public interface SendTokenToEmailService {

   void sendTokenToEmail(Member member, String token);
}

