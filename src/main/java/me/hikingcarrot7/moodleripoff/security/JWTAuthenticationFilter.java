package me.hikingcarrot7.moodleripoff.security;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

import java.security.Principal;
import java.util.List;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTAuthenticationFilter implements ContainerRequestFilter {
  @Context
  private SecurityContext securityContext;

  @Override
  public void filter(ContainerRequestContext requestContext) {
    System.out.println("JWTAuthenticationFilter.filter");
    UserPrincipal userPrincipal = new UserPrincipal();

    requestContext.setSecurityContext(new SecurityContext() {

      @Override
      public Principal getUserPrincipal() {
        return userPrincipal;
      }

      @Override
      public boolean isUserInRole(String role) {
        System.out.println("JWTAuthenticationFilter.isUserInRole");
        List<String> roles = userPrincipal.getRoles();
        System.out.println(role);
        return true;
      }

      @Override
      public boolean isSecure() {
        System.out.println("JWTAuthenticationFilter.isSecure");
        return securityContext.isSecure();
      }

      @Override
      public String getAuthenticationScheme() {
        System.out.println("JWTAuthenticationFilter.getAuthenticationScheme");
        return "Bearer";
      }

    });
  }

}
