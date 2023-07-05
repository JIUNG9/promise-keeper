package com.studygroup.service.user;

import com.studygroup.dto.member.MemberSignUpForm;
import org.springframework.stereotype.Service;

@Service
public interface SignUpService {
  void signUp(MemberSignUpForm signUpForm);
}
