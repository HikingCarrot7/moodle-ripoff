package me.hikingcarrot7.moodleripoff.examples;

import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {
  @Inject
  private GreetingService greetingService;

  @GET
  @Path("{visitor}")
  public JsonObject hello(@PathParam("visitor") final String visitor) {
    return greetingService.greet(visitor);
  }

}
