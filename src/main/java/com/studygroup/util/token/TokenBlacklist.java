package com.studygroup.util.token;

import java.util.HashSet;
import java.util.Set;

public class TokenBlacklist {

  private static final Set<String> BLACKLIST = new HashSet<>();

  public static void addTokenToBlacklist(String token) {
    BLACKLIST.add(token);
  }

  public static boolean isTokenBlacklisted(String token) {
    return BLACKLIST.contains(token);
  }
}
