package com.studygroup.auth.token;

import javax.servlet.http.Cookie;

public interface TokenHandler<T> {
  public Cookie create(T token);
  public void remove(T token);
  public Cookie extend(T token);
}
