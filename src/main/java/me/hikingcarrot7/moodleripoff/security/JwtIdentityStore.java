package me.hikingcarrot7.moodleripoff.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.Set;

@ApplicationScoped
public class JwtIdentityStore implements IdentityStore {

  @Override
  public CredentialValidationResult validate(Credential credential) {
    System.out.println("JwtIdentityStore.validate");
    return new CredentialValidationResult("test", Set.of("student"));
  }

}
