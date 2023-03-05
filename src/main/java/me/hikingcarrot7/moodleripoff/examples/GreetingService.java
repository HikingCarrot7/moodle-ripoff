package me.hikingcarrot7.moodleripoff.examples;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import lombok.extern.java.Log;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
@Log
public class GreetingService {
  @Inject
  private ConfigPropertyProvider propertyProvider;

  public JsonObject greet(final String name) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return Json.createObjectBuilder()
        .add("greeting", "Hello there " + name)
        .add("message", "Getting started with Jakarta EE!")
        .add("platform", "Jakarta EE")
        .add("platformVersion", propertyProvider.getJakartaVersion())
        .add("implementation", propertyProvider.getApplicationServer())
        .add("date", LocalDateTime.now(ZoneOffset.UTC).format(formatter))
        .build();
  }

}
