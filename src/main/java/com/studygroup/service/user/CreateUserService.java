package com.studygroup.service.user;

import com.studygroup.dto.member.MemberSignUpForm;
import com.studygroup.domain.Member;
import com.studygroup.repository.UserRepository;
import com.studygroup.service.common.CreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService implements CreateService<MemberSignUpForm> {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  @Override
  public void create(MemberSignUpForm signUpForm) {
    Member member = Member.
        builder().
        email(signUpForm.getEmail()).
        name(signUpForm.getName()).
        password(bCryptPasswordEncoder.encode(signUpForm.getPassword())).
        gender(signUpForm.getGender()).
        age(signUpForm.getAge()).
        build();
    userRepository.save(member);
  }
}
