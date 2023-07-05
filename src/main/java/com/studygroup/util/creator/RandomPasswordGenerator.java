package com.studygroup.util.creator;

import java.security.SecureRandom;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RandomPasswordGenerator {

  private RandomPasswordGenerator(){

  }

  public static String generateRandomPassword() {
    int length = 10;
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
    SecureRandom secureRandom = new SecureRandom();
    StringBuilder password = new StringBuilder();

    for (int i = 0; i < length; i++) {
      int randomIndex = secureRandom.nextInt(characters.length());
      password.append(characters.charAt(randomIndex));
    }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder.encode(password.toString());
  }
}
