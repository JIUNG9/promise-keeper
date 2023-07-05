package com.studygroup.service.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DuplicationCheckerService<T> implements CheckDuplicationService<T> {
  private final DuplicationCheckerProvider<T> provider;

  @Override
  public boolean isDuplicated(T object) {
    return provider.isDuplicated(object);
  }
}
