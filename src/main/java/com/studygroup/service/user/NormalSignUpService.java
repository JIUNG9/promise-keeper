package com.studygroup.service.user;

import com.studygroup.entity.Member;
import com.studygroup.repository.UserRepository;
import com.studygroup.util.constant.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NormalSignUpService implements SignUpService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void signUp(Member member) {
//        Email duplication check
        String memberEmail = member.getEmail();

        if (Optional.ofNullable(userRepository.findByEmail(memberEmail)).isEmpty()) {

            Member insertMember = Member.
                    builder().
                    email(member.getEmail()).
                    name(member.getName()).
                    email_verified(false).
                    password(bCryptPasswordEncoder.encode(member.getPassword())).
                    build();
            userRepository.save(insertMember);
        } else {
            throw new IllegalArgumentException(ErrorCode.EMAIL_IS_DUPLICATED.getMessage());
        }

    }
}
