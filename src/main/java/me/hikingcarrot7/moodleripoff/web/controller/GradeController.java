package me.hikingcarrot7.moodleripoff.web.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import me.hikingcarrot7.moodleripoff.model.Grade;
import me.hikingcarrot7.moodleripoff.service.GradeService;
import me.hikingcarrot7.moodleripoff.web.dto.GradeDTO;
import me.hikingcarrot7.moodleripoff.web.dto.mapper.GradeMapper;

import java.net.URI;

@Path("/grades")
@RolesAllowed({"teacher"})
public class GradeController {
  @Inject private GradeService gradeService;
  @Inject private GradeMapper gradeMapper;

  @GET
  @Path("/{gradeId:[0-9]+}")
  public Response getGradeById(@PathParam("gradeId") Long gradeId) {
    Grade result = gradeService.getGradeById(gradeId);
    return Response
        .ok(gradeMapper.toGradeDto(result))
        .build();
  }

  @POST
  public Response createGrade(
      @QueryParam("submissionId") Long submissionId,
      @Valid GradeDTO gradeDto,
      @Context UriInfo uriInfo
  ) {
    Grade grade = gradeMapper.toGrade(gradeDto);
    Grade result = gradeService.createGrade(submissionId, grade);
    URI uri = uriInfo.getAbsolutePathBuilder().path(result.getId().toString()).build();
    return Response
        .created(uri)
        .entity(gradeMapper.toGradeDto(result))
        .build();
  }

  @PUT
  @Path("/{gradeId:[0-9]+}")
  public Response updateGrade(
      @PathParam("gradeId") Long gradeId,
      @Valid GradeDTO gradeDto
  ) {
    Grade grade = gradeMapper.toGrade(gradeDto);
    Grade result = gradeService.updateGrade(gradeId, grade);
    return Response
        .ok(gradeMapper.toGradeDto(result))
        .build();
  }

  @DELETE
  @Path("/{gradeId:[0-9]+}")
  public Response deleteGrade(@PathParam("gradeId") Long gradeId) {
    gradeService.deleteGrade(gradeId);
    return Response
        .noContent()
        .build();
  }

}
