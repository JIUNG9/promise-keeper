package com.studygroup.service.email;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("SendPasswordResetToken")
public interface SendTokenToEmailService {

   void sendTokenToEmail(Long memberId, String token);
}

