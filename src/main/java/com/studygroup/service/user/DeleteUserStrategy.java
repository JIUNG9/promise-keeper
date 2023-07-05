package com.studygroup.service.user;

import org.springframework.stereotype.Service;

@Service
public interface DeleteUserStrategy<T> {
  public void delete(T object);
}
