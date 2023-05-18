package com.studygroup.service.user;

import com.studygroup.entity.Member;
import com.studygroup.enums.Role;
import com.studygroup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("UserKickService")
@RequiredArgsConstructor
public class UserKickService implements UserRemovalService {

    private final UserRepository userRepository;

    @Override
    public void removeUser(Member member) {


        Member updateUserIsKicked = Member.
                                        builder().
                                        role(Role.ROLE_KICKED).
                                        gender(member.getGender()).
                                        email(member.getEmail()).
                                        age(member.getAge()).
                                        name(member.getName()).
                                        password(member.getPassword()).
                                        id(member.getId()).
                                        build();

        userRepository.save(updateUserIsKicked);

    }
}
