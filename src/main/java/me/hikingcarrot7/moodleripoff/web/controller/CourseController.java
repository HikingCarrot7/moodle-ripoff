package me.hikingcarrot7.moodleripoff.web.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import me.hikingcarrot7.moodleripoff.model.Course;
import me.hikingcarrot7.moodleripoff.model.Student;
import me.hikingcarrot7.moodleripoff.service.CourseService;
import me.hikingcarrot7.moodleripoff.web.dto.CourseDTO;
import me.hikingcarrot7.moodleripoff.web.dto.mapper.CourseMapper;
import me.hikingcarrot7.moodleripoff.web.dto.mapper.StudentMapper;

import java.util.List;

@Path("/courses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseController {
  @Inject private CourseService courseService;
  @Inject private CourseMapper courseMapper;
  @Inject private StudentMapper studentMapper;

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
  public Response getEnrolledStudents(@PathParam("courseId") Long courseId) {
    List<Student> result = courseService.getEnrolledStudents(courseId);
    return Response
        .ok(studentMapper.toStudentDtoList(result))
        .build();
  }

  @POST
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
  public Response updateCourse(
      @PathParam("courseId") Long courseId,
      @QueryParam("teacherId") Long teacherId,
      @Valid CourseDTO courseDto
  ) {
    Course course = courseMapper.toCourse(courseDto);
    Course result = courseService.updateCourse(teacherId, courseId, course);
    return Response
        .ok(courseMapper.toCourseDto(result))
        .build();
  }

  @DELETE
  @Path("/{courseId:[0-9]+}")
  public Response removeCourseFromTeacher(
      @QueryParam("teacherId") Long teacherId,
      @PathParam("courseId") Long courseId
  ) {
    Course result = courseService.removeCourseFromTeacher(teacherId, courseId);
    return Response
        .ok(courseMapper.toCourseDto(result))
        .build();
  }

}
