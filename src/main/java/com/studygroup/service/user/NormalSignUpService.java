package com.studygroup.service.user;

import com.studygroup.entity.Member;
import com.studygroup.enums.Role;
import com.studygroup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("NormalSignUpService")
@RequiredArgsConstructor
public class NormalSignUpService implements SignUpService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void signUp(Member member) {

            Member insertMember = Member.
                    builder().
                    email(member.getEmail()).
                    name(member.getName()).
                    gender(member.getGender()).
                    role(Role.ROLE_USER).
                    age(member.getAge()).
                    password(bCryptPasswordEncoder.encode(member.getPassword())).
                    build();

                userRepository.save(insertMember);

    }
}
