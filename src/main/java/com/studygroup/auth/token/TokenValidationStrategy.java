package com.studygroup.auth.token;

public interface TokenValidationStrategy<T extends TokenData> {
  boolean performTokenOperation(T token);
}
