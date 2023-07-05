package com.studygroup.util.cookie;

import javax.servlet.http.Cookie;

public class CookieBuilder {

  private String name;
  private String value;
  private Integer maxAge;
  private String domain;

  private CookieBuilder() {

  }

  public  static CookieBuilder defaultBuilder(String name, String value) {
    CookieBuilder builder = new CookieBuilder();
    builder.name = name;
    builder.value = value;
    return builder;
  }


  public CookieBuilder defaultConstructorWithOtherDefault() {
    this.domain ="localhost";
    this.maxAge = 60 * 60 * 30;

    return this;
  }

  public  Cookie build() {
    Cookie cookie = new Cookie(name, value);
    cookie.setHttpOnly(true);
    cookie.setMaxAge(maxAge);
    cookie.setDomain(domain);
    cookie.setPath("/");
    return cookie;
  }
}