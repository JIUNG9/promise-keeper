package com.studygroup.service.user;

import org.springframework.stereotype.Service;

@Service
public interface VerifyTheTokenService {
    void verifyTheToken(String token);
}
