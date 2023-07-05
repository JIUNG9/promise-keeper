package com.studygroup.service.user;

import com.studygroup.domain.Member;
import com.studygroup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("UpdateUserIsEnableService")
public class UpdateUserIsEnableServiceImpl implements UpdateUserIsEnableService {

  private final UserRepository userRepository;

  @Override
  public void update(Member member, boolean isEnabled) {

    Member updatePasswordMember = Member.
        builder().
        name(member.getName()).
        password(member.getPassword()).
        id(member.getId()).
        email(member.getEmail()).
        age(member.getAge()).
        emailVerified(isEnabled).
        role(member.getRole()).
        gender(member.getGender()).
        build();

    userRepository.save(updatePasswordMember);
  }
}
