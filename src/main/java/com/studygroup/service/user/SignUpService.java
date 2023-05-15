package com.studygroup.service.user;

import com.studygroup.entity.Member;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SignUpService {
    void signUp(Member member);
}
