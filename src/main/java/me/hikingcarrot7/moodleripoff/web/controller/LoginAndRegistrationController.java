package me.hikingcarrot7.moodleripoff.web.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.hikingcarrot7.moodleripoff.model.Student;
import me.hikingcarrot7.moodleripoff.model.Teacher;
import me.hikingcarrot7.moodleripoff.security.service.LoginAndRegistrationService;
import me.hikingcarrot7.moodleripoff.web.dto.TeacherDTO;
import me.hikingcarrot7.moodleripoff.web.dto.auth.LoginRequestDTO;
import me.hikingcarrot7.moodleripoff.web.dto.auth.TokenDTO;
import me.hikingcarrot7.moodleripoff.web.dto.mapper.StudentMapper;
import me.hikingcarrot7.moodleripoff.web.dto.mapper.TeacherMapper;
import me.hikingcarrot7.moodleripoff.web.dto.student.StudentDTO;

import java.net.URI;

@Path("/auth")
@PermitAll
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginAndRegistrationController {
  @Inject private LoginAndRegistrationService loginAndRegistrationService;
  @Inject private StudentMapper studentMapper;
  @Inject private TeacherMapper teacherMapper;

  @POST
  @Path("/login/students")
  public Response loginStudent(@Valid LoginRequestDTO loginRequestDto) {
    TokenDTO token = loginAndRegistrationService.loginStudent(loginRequestDto);
    return Response.ok(token).build();
  }

  @POST
  @Path("/login/teachers")
  public Response loginTeacher(@Valid LoginRequestDTO loginRequestDto) {
    TokenDTO token = loginAndRegistrationService.loginTeacher(loginRequestDto);
    return Response.ok(token).build();
  }

  @POST
  @Path("/register/students")
  public Response registerStudent(@Valid StudentDTO studentDto) {
    Student student = studentMapper.toStudent(studentDto);
    TokenDTO token = loginAndRegistrationService.registerStudent(student);
    return Response
        .created(URI.create("/api/students/" + token.getId()))
        .entity(token)
        .build();
  }

  @POST
  @Path("/register/teachers")
  public Response registerTeacher(@Valid TeacherDTO teacherDto) {
    Teacher teacher = teacherMapper.toTeacher(teacherDto);
    TokenDTO token = loginAndRegistrationService.registerTeacher(teacher);
    return Response
        .created(URI.create("/api/teachers/" + token.getId()))
        .entity(token)
        .build();
  }

}
