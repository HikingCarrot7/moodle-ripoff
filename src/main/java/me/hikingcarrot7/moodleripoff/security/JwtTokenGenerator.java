package me.hikingcarrot7.moodleripoff.security;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import static com.nimbusds.jose.JWSAlgorithm.RS256;

@ApplicationScoped
public class JwtTokenGenerator {
  @Inject
  @ConfigProperty(name = "jwt.private.key")
  private String privateKeyString;

  @Inject
  @ConfigProperty(name = "jwt.expiration.time.seconds")
  private int expirationTimeSeconds;

  public String generateJWTString(SecurityUser securityUser) {
    long currentTimeInSecs = (System.currentTimeMillis() / 1000);
    long expirationTime = currentTimeInSecs + expirationTimeSeconds;

    JsonObject jwtJson = Json.createObjectBuilder()
        .add(Claims.iat.name(), currentTimeInSecs)
        .add(Claims.auth_time.name(), currentTimeInSecs)
        .add(Claims.exp.name(), expirationTime)
        .add(Claims.jti.name(), "a-123")
        .add(Claims.iss.name(), "https://hikingcarrot7.me")
        .add("id", securityUser.getId())
        .add(Claims.sub.name(), securityUser.getEmail())
        .add(Claims.upn.name(), securityUser.getEmail())
        .add(Claims.groups.name(), Json.createArrayBuilder()
            .add(securityUser.getRole())
            .build())
        .build();

    JWSHeader header = new JWSHeader.Builder(RS256)
        .keyID("jwt.key")
        .type(JOSEObjectType.JWT)
        .build();

    try {
      JWTClaimsSet claimSet = JWTClaimsSet.parse(jwtJson.toString());
      SignedJWT signedJWT = new SignedJWT(header, claimSet);
      PrivateKey privateKey = readPrivateKey(privateKeyString);
      RSASSASigner signer = new RSASSASigner(privateKey);
      signedJWT.sign(signer);
      return signedJWT.serialize();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private PrivateKey readPrivateKey(String privateKey) throws Exception {
    byte[] decodedKey = Base64.getDecoder().decode(privateKey);
    return KeyFactory
        .getInstance("RSA")
        .generatePrivate(new PKCS8EncodedKeySpec(decodedKey));
  }

}
