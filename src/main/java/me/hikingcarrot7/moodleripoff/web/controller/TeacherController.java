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
import me.hikingcarrot7.moodleripoff.model.Teacher;
import me.hikingcarrot7.moodleripoff.service.TeacherService;
import me.hikingcarrot7.moodleripoff.web.dto.TeacherDTO;
import me.hikingcarrot7.moodleripoff.web.dto.mapper.TeacherMapper;
import me.hikingcarrot7.moodleripoff.web.dto.teacher.UpdateTeacherRequestDTO;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.net.URI;

@Path("/teachers")
@RolesAllowed("teacher")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherController {
  @Inject private JsonWebToken principal;
  @Inject private TeacherService teacherService;
  @Inject private TeacherMapper teacherMapper;

  @GET
  @Path("/me")
  public Response getLoggedTeacher() {
    Long teacherId = getLoggedTeacherId();
    Teacher result = teacherService.getTeacherById(teacherId);
    return Response
        .ok(teacherMapper.toTeacherDto(result))
        .build();
  }

  @GET
  @Path("/{teacherId}")
  @RolesAllowed({"teacher", "student"})
  public Response getTeacherById(@PathParam("teacherId") Long teacherId) {
    Teacher result = teacherService.getTeacherById(teacherId);
    return Response
        .ok(teacherMapper.toTeacherDto(result))
        .build();
  }

  @POST
  public Response createTeacher(@Valid TeacherDTO teacherDto, @Context UriInfo uriInfo) {
    Teacher newTeacher = teacherMapper.toTeacher(teacherDto);
    Teacher result = teacherService.createTeacher(newTeacher);
    URI uri = uriInfo.getAbsolutePathBuilder().path(result.getId().toString()).build();
    return Response
        .created(uri)
        .entity(teacherMapper.toTeacherDto(result))
        .build();
  }

  @PUT
  public Response updateTeacher(@Valid UpdateTeacherRequestDTO updateTeacherRequestDTO) {
    ensurePasswordsMatch(updateTeacherRequestDTO);
    Long teacherId = getLoggedTeacherId();
    Teacher teacher = teacherMapper.toTeacher(updateTeacherRequestDTO);
    Teacher result = teacherService.updateTeacher(teacherId, teacher);
    return Response
        .ok(teacherMapper.toTeacherDto(result))
        .build();
  }

  private Long getLoggedTeacherId() {
    return ((JsonNumber) principal.getClaim("id")).longValue();
  }

  private void ensurePasswordsMatch(UpdateTeacherRequestDTO updateTeacherRequestDTO) {
    if (!updateTeacherRequestDTO.getPassword().equals(updateTeacherRequestDTO.getRepeatPassword())) {
      throw new BadRequestException("Passwords do not match");
    }
  }

}
