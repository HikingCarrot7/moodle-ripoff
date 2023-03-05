package me.hikingcarrot7.moodleripoff.web.exception.mapper;

import jakarta.annotation.Priority;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Provider
@Priority(Priorities.USER)
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

  @Override
  public Response toResponse(ConstraintViolationException e) {
    var errors = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();
    Map<String, List<String>> errorsMap = new HashMap<>();
    errorsMap.put("errors", errors);
    return Response.status(400).entity(errorsMap).build();
  }

}
