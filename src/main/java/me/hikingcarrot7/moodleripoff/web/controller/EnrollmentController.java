package me.hikingcarrot7.moodleripoff.web.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import me.hikingcarrot7.moodleripoff.model.Enrollment;
import me.hikingcarrot7.moodleripoff.model.EnrollmentRequest;
import me.hikingcarrot7.moodleripoff.service.EnrollmentService;
import me.hikingcarrot7.moodleripoff.web.dto.mapper.EnrollmentMapper;

import java.net.URI;
import java.util.List;

@Path("/enrollments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EnrollmentController {
  @Inject private EnrollmentService enrollmentService;
  @Inject private EnrollmentMapper enrollmentMapper;

  @GET
  public Response getEnrollmentsOfCourse(@QueryParam("courseId") Long courseId) {
    List<Enrollment> result = enrollmentService.getEnrolledStudents(courseId);
    return Response
        .ok(enrollmentMapper.toEnrollmentDtoList(result))
        .build();
  }

  @POST
  public Response enrollStudent(
      @QueryParam("courseId") Long courseId,
      @QueryParam("studentId") Long studentId,
      @Valid EnrollmentRequest request,
      @Context UriInfo uriInfo
  ) {
    Enrollment result = enrollmentService.enrollStudent(studentId, courseId, request);
    URI uri = uriInfo.getAbsolutePathBuilder().path(result.getId().toString()).build();
    return Response.created(uri).build();
  }

  @DELETE
  public Response removeStudentEnrollment(
      @QueryParam("courseId") Long courseId,
      @QueryParam("studentId") Long studentId
  ) {
    enrollmentService.removeStudentEnrollment(studentId, courseId);
    return Response.noContent().build();
  }

}
