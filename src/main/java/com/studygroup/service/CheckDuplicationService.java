package com.studygroup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface CheckDuplicationService {
    boolean isDuplicated(String param);
}
