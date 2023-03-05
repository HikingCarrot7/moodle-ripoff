package me.hikingcarrot7.moodleripoff.web.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import me.hikingcarrot7.moodleripoff.model.Student;
import me.hikingcarrot7.moodleripoff.service.StudentService;
import me.hikingcarrot7.moodleripoff.web.dto.StudentDTO;
import me.hikingcarrot7.moodleripoff.web.dto.mapper.StudentMapper;

import java.net.URI;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentController {
  @Inject private StudentService studentService;
  @Inject private StudentMapper studentMapper;

  @GET
  @Path("/{id:[0-9]+}")
  public Response getStudentById(@PathParam("id") Long id) {
    Student result = studentService.getStudentById(id);
    return Response
        .ok(studentMapper.toStudentDto(result))
        .build();
  }

  @POST
  public Response createStudent(@Valid StudentDTO studentDto, @Context UriInfo uriInfo) {
    Student newStudent = studentMapper.toStudent(studentDto);
    Student result = studentService.createStudent(newStudent);
    URI uri = uriInfo.getAbsolutePathBuilder().path(result.getId().toString()).build();
    return Response
        .created(uri)
        .entity(studentMapper.toStudentDto(result))
        .build();
  }

}
