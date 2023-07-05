package com.studygroup.auth.token;

public interface UserVerificationByTokenTypeStrategy<T> {

  public boolean performTokenOperation(T token);
}
