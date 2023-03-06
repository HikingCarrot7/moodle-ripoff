package me.hikingcarrot7.moodleripoff.util;

import jakarta.ejb.Singleton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Singleton
public class PasswordEncoder {

  public String encode(String password) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(password.getBytes());
      byte[] digest = md.digest();
      return Base64.getEncoder().encodeToString(digest);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean matches(String originalPassword, String hashedPassword) {
    try {
      return encode(originalPassword).equals(hashedPassword);
    } catch (RuntimeException e) {
      return false;
    }
  }

}
