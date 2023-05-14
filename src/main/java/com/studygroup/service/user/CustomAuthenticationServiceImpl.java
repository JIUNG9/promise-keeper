package com.studygroup.service.user;

import com.studygroup.entity.Member;
import com.studygroup.repository.UserRepository;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.lambda.BindParameterSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomAuthenticationServiceImpl implements CustomAuthenticationService {

    private final UserRepository userRepository;
    @Override
    public Member loadUserByUsername(String email) {
        return Optional.
                ofNullable(userRepository.findByEmail(email)).
                orElseThrow(BindParameterSupplier.bind(
                        UsernameNotFoundException::new, ErrorCode.EMAIL_IS_NOT_EXISTED.getMessage()));
    }
}
