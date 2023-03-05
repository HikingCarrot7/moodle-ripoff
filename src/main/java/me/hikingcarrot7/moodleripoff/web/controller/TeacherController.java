package me.hikingcarrot7.moodleripoff.web.controller;

import jakarta.inject.Inject;
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

import java.net.URI;

@Path("/teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherController {
  @Inject private TeacherService teacherService;
  @Inject private TeacherMapper teacherMapper;

  @GET
  @Path("/{id}")
  public Response getTeacherById(@PathParam("id") Long id) {
    Teacher result = teacherService.getTeacherById(id);
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
  @Path("/{teacherId:[0-9]+}}")
  public Response updateTeacher(
      @PathParam("teacherId") Long teacherId,
      @Valid TeacherDTO teacherDto
  ) {
    Teacher teacher = teacherMapper.toTeacher(teacherDto);
    Teacher result = teacherService.updateTeacher(teacherId, teacher);
    return Response
        .ok(teacherMapper.toTeacherDto(result))
        .build();
  }

}
