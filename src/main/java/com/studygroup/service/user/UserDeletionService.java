package com.studygroup.service.user;


import com.studygroup.entity.Member;
import com.studygroup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("UserDeletionService")
@RequiredArgsConstructor
public class UserDeletionService implements UserRemovalService {

    private final UserRepository userRepository;

    @Override
    public void removeUser(Member member) {
        userRepository.delete(member);
    }
}
