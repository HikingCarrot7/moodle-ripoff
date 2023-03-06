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
import me.hikingcarrot7.moodleripoff.model.Course;
import me.hikingcarrot7.moodleripoff.model.Enrollment;
import me.hikingcarrot7.moodleripoff.service.CourseService;
import me.hikingcarrot7.moodleripoff.web.dto.CourseDTO;
import me.hikingcarrot7.moodleripoff.web.dto.mapper.CourseMapper;
import me.hikingcarrot7.moodleripoff.web.dto.mapper.EnrollmentMapper;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;

@Path("/courses")
@RolesAllowed({"student", "teacher"})
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseController {
  @Inject private JsonWebToken principal;
  @Inject private CourseService courseService;
  @Inject private CourseMapper courseMapper;
  @Inject private EnrollmentMapper enrollmentMapper;

  @GET
  public Response getAllCourses() {
    List<Course> result = courseService.getAllCourses();
    return Response
        .ok(courseMapper.toCourseDtoList(result))
        .build();
  }

  @GET
  @Path("/student")
  @RolesAllowed("student")
  public Response getCoursesOfLoggedStudent() {
    Long studentId = getLoggedUserId();
    List<Course> result = courseService.getCoursesByStudentId(studentId);
    return Response
        .ok(courseMapper.toCourseDtoList(result))
        .build();
  }

  @GET
  @Path("/teacher")
  @RolesAllowed("teacher")
  public Response getCoursesOfLoggedTeacher() {
    Long teacherId = getLoggedUserId();
    List<Course> result = courseService.getCoursesByTeacherId(teacherId);
    return Response
        .ok(courseMapper.toCourseDtoList(result))
        .build();
  }

  @GET
  @Path("/{courseId:[0-9]+}")
  public Response getCourseById(@PathParam("courseId") Long courseId) {
    Course result = courseService.getCourseById(courseId);
    return Response
        .ok(courseMapper.toCourseDto(result))
        .build();
  }

  @GET
  @Path("/{courseId:[0-9]+}/students")
  public Response getEnrollments(@PathParam("courseId") Long courseId) {
    List<Enrollment> result = courseService.getEnrollments(courseId);
    return Response
        .ok(enrollmentMapper.toEnrollmentDtoList(result))
        .build();
  }

  @POST
  @RolesAllowed("teacher")
  public Response addCourseToTeacher(
      @QueryParam("teacherId") Long teacherId,
      @Valid CourseDTO courseDto,
      @Context UriInfo uriInfo
  ) {
    Course newCourse = courseMapper.toCourse(courseDto);
    Course result = courseService.addCourseToTeacher(teacherId, newCourse);
    return Response
        .created(uriInfo.getAbsolutePathBuilder().path(result.getId().toString()).build())
        .entity(courseMapper.toCourseDto(result))
        .build();
  }

  @PUT
  @Path("/{courseId:[0-9]+}")
  @RolesAllowed("teacher")
  public Response updateCourse(
      @PathParam("courseId") Long courseId,
      @Valid CourseDTO courseDto
  ) {
    Long teacherId = getLoggedUserId();
    Course course = courseMapper.toCourse(courseDto);
    Course result = courseService.updateCourse(teacherId, courseId, course);
    return Response
        .ok(courseMapper.toCourseDto(result))
        .build();
  }

  @DELETE
  @Path("/{courseId:[0-9]+}")
  @RolesAllowed("teacher")
  public Response removeCourseFromTeacher(@PathParam("courseId") Long courseId) {
    Long teacherId = getLoggedUserId();
    Course result = courseService.removeCourseFromTeacher(teacherId, courseId);
    return Response
        .ok(courseMapper.toCourseDto(result))
        .build();
  }

  private Long getLoggedUserId() {
    return ((JsonNumber) principal.getClaim("id")).longValue();
  }

}
