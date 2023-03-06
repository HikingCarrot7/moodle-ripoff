package me.hikingcarrot7.moodleripoff.web.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.json.JsonNumber;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import me.hikingcarrot7.moodleripoff.model.Assignment;
import me.hikingcarrot7.moodleripoff.service.AssignmentService;
import me.hikingcarrot7.moodleripoff.web.dto.AssignmentDTO;
import me.hikingcarrot7.moodleripoff.web.dto.mapper.AssignmentMapper;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;

@Path("/assignments")
@RolesAllowed({"teacher", "student"})
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AssignmentController {
  @Inject private JsonWebToken principal;
  @Inject private AssignmentService assignmentService;
  @Inject private AssignmentMapper assignmentMapper;

  @GET
  @Path("/course/{courseId:[0-9]+}")
  public Response getAssignmentsByCourseId(@PathParam("courseId") Long courseId) {
    List<Assignment> result = assignmentService.getAssignmentsByCourseId(courseId);
    return Response
        .ok(assignmentMapper.toAssignmentDtoList(result))
        .build();
  }

  @GET
  @Path("/student")
  public Response getAssignmentsOfLoggedStudent() {
    Long studentId = getLoggedUserId();
    List<Assignment> result = assignmentService.getAssignmentsByStudentId(studentId);
    return Response
        .ok(assignmentMapper.toAssignmentDtoList(result))
        .build();
  }

  @GET
  @Path("/student/{studentId:[0-9]+}")
  public Response getAssignmentsByStudentId(@PathParam("studentId") Long studentId) {
    List<Assignment> result = assignmentService.getAssignmentsByStudentId(studentId);
    return Response
        .ok(assignmentMapper.toAssignmentDtoList(result))
        .build();
  }

  @GET
  @Path("/{assignmentId:[0-9]+}")
  public Response getAssignmentById(@PathParam("assignmentId") Long assignmentId) {
    Assignment result = assignmentService.getAssignmentById(assignmentId);
    return Response
        .ok(assignmentMapper.toAssignmentDto(result))
        .build();
  }

  @POST
  public Response addAssignmentToCourse(
      @QueryParam("courseId") Long courseId,
      @Context UriInfo uriInfo,
      @Valid AssignmentDTO assignmentDto
  ) {
    Assignment assignment = assignmentMapper.toAssignment(assignmentDto);
    Assignment result = assignmentService.addAssignmentToCourse(courseId, assignment);
    return Response
        .created(uriInfo.getAbsolutePathBuilder().path(result.getId().toString()).build())
        .entity(assignmentMapper.toAssignmentDto(result))
        .build();
  }

  @POST
  @Path("/{assignmentId:[0-9]+}")
  @RolesAllowed("student")
  public Response toggleAssignmentAsCompleted(@PathParam("assignmentId") Long assignmentId) {
    Long studentId = getLoggedUserId();
    var result = assignmentService.toggleAssignmentAsCompleted(assignmentId, studentId);
    return Response
        .ok(result)
        .build();
  }

  @PUT
  @Path("/{assignmentId:[0-9]+}")
  public Response updateAssignment(
      @PathParam("assignmentId") Long assignmentId,
      @Valid AssignmentDTO assignmentDto
  ) {
    Assignment assignment = assignmentMapper.toAssignment(assignmentDto);
    Assignment result = assignmentService.updateAssignment(assignmentId, assignment);
    return Response
        .ok(assignmentMapper.toAssignmentDto(result))
        .build();
  }

  @DELETE
  @Path("/{assignmentId:[0-9]+}")
  public Response removeAssignmentFromCourse(@PathParam("assignmentId") Long assignmentId) {
    Assignment result = assignmentService.removeAssignmentFromCourse(assignmentId);
    return Response
        .ok(assignmentMapper.toAssignmentDto(result))
        .build();
  }

  private Long getLoggedUserId() {
    return ((JsonNumber) principal.getClaim("id")).longValue();
  }

}
