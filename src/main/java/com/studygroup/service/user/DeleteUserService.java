package com.studygroup.service.user;

import org.springframework.stereotype.Service;

@Service

public class DeleteUserService<T> implements UserDeleteService<T> {
  private final DeleteUserStrategy<T> deleteUserStrategy;

  public DeleteUserService(DeleteUserStrategy<T> deleteUserStrategy) {
    this.deleteUserStrategy = deleteUserStrategy;
  }

  @Override
  public void delete(T memberSpecification) {
    deleteUserStrategy.delete(memberSpecification);
  }


}
