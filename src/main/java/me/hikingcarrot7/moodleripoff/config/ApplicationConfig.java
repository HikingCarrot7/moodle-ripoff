package me.hikingcarrot7.moodleripoff.config;

import jakarta.annotation.security.DeclareRoles;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.auth.LoginConfig;

@ApplicationPath("/api")
@LoginConfig(authMethod = "MP-JWT")
@DeclareRoles({"teacher", "student"})
public class ApplicationConfig extends Application {

}
