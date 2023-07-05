package com.studygroup.service.common;

import org.springframework.stereotype.Service;

@Service
public interface DuplicationCheckerProvider<T> {
  boolean isDuplicated(T specification);
}
