package com.studygroup.service.user;

import com.studygroup.auth.token.TokenData;
import org.springframework.stereotype.Service;

@Service
public interface
VerifyUserEmailTokenStrategy<T extends TokenData> {

  boolean verifyTheToken(T token);
}
