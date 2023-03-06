package me.hikingcarrot7.moodleripoff.web.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.json.JsonNumber;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.hikingcarrot7.moodleripoff.model.Student;
import me.hikingcarrot7.moodleripoff.service.StudentService;
import me.hikingcarrot7.moodleripoff.web.dto.mapper.StudentMapper;
import me.hikingcarrot7.moodleripoff.web.dto.student.UpdateStudentRequestDTO;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/students")
@RolesAllowed({"teacher", "student"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentController {
  @Inject private JsonWebToken principal;
  @Inject private StudentService studentService;
  @Inject private StudentMapper studentMapper;

  @GET
  @Path("/me")
  @RolesAllowed("student")
  public Response getLoggedStudent() {
    Long studentId = getLoggedStudentId();
    Student result = studentService.getStudentById(studentId);
    return Response
        .ok(studentMapper.toStudentDto(result))
        .build();
  }

  @GET
  @Path("/{studentId:[0-9]+}")
  public Response getStudentById(@PathParam("studentId") Long studentId) {
    Student result = studentService.getStudentById(studentId);
    return Response
        .ok(studentMapper.toStudentDto(result))
        .build();
  }

  @PUT
  public Response updateStudent(@Valid UpdateStudentRequestDTO updateStudentRequestDto) {
    ensurePasswordMatches(updateStudentRequestDto);
    Long studentId = getLoggedStudentId();
    Student student = studentMapper.toStudent(updateStudentRequestDto);
    Student result = studentService.updateStudent(studentId, student);
    return Response
        .ok(studentMapper.toStudentDto(result))
        .build();
  }

  private Long getLoggedStudentId() {
    return ((JsonNumber) principal.getClaim("id")).longValue();
  }

  private void ensurePasswordMatches(UpdateStudentRequestDTO updateStudentRequestDTO) {
    if (!updateStudentRequestDTO.getPassword().equals(updateStudentRequestDTO.getRepeatPassword())) {
      throw new BadRequestException("Password and confirm password do not match");
    }
  }

}
