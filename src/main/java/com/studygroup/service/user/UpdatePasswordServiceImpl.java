package com.studygroup.service.user;

import com.studygroup.entity.Member;
import com.studygroup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("UpdatePasswordService")
public class UpdatePasswordServiceImpl implements UpdateUserPasswordService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void update(Member member, String password) {

        Member updatePasswordMember = Member.
                                        builder().
                                        name(member.getName()).
                                        password(bCryptPasswordEncoder.encode(password)).
                                        id(member.getId()).
                                        email(member.getEmail()).
                                        age(member.getAge()).
                                        emailVerified(member.isEmailVerified()).
                                        role(member.getRole()).
                                        gender(member.getGender()).
                                        build();

        userRepository.save(updatePasswordMember);

    }
}
