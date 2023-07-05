package com.studygroup.service.common;

import org.springframework.stereotype.Service;

@Service
public interface CheckDuplicationService<T> {
  boolean isDuplicated(T object);

}
