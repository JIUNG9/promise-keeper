package com.studygroup.service.user;

import com.studygroup.repository.UserRepository;
import com.studygroup.service.CheckDuplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("CheckEmailDuplicationService")
@RequiredArgsConstructor
public class CheckEmailDuplicationService implements CheckDuplicationService {

    private final UserRepository userRepository;

    @Override
    public boolean isDuplicated(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email)).isPresent();
    }
}
