package me.hikingcarrot7.moodleripoff.web.controller;

import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import me.hikingcarrot7.moodleripoff.model.Submission;
import me.hikingcarrot7.moodleripoff.model.spec.SubmissionSpec;
import me.hikingcarrot7.moodleripoff.service.SubmissionService;
import me.hikingcarrot7.moodleripoff.web.dto.SubmissionDTO;
import me.hikingcarrot7.moodleripoff.web.dto.mapper.SubmissionMapper;

import java.net.URI;

@Path("/submissions")
@Produces(MediaType.APPLICATION_JSON)
public class SubmissionController {
  @Inject private SubmissionService submissionService;
  @Inject private SubmissionMapper submissionMapper;

  @GET
  @Path("/{submissionId:[0-9]+}")
  public Response getSubmissionById(@PathParam("submissionId") Long submissionId) {
    Submission submission = submissionService.getSubmissionById(submissionId);
    return Response
        .ok(submissionMapper.toSubmissionDTO(submission))
        .build();
  }

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Response submitAssignment(
      @QueryParam("studentId") Long studentId,
      @QueryParam("assignmentId") Long assignmentId,
      @FormParam("assignment") String submissionJson,
      @FormParam("file") EntityPart file,
      @Context UriInfo uriInfo
  ) {
    SubmissionDTO submissionDto = parseToSubmissionDto(submissionJson);
    Submission submission = submissionMapper.toSubmission(submissionDto);
    var submissionSpec = SubmissionSpec.builder()
        .submission(submission)
        .studentId(studentId)
        .assignmentId(assignmentId)
        .file(file.getContent())
        .fileName(file.getFileName().get())
        .build();
    Submission result = submissionService.createSubmission(submissionSpec);
    URI uri = uriInfo.getAbsolutePathBuilder().path(result.getId().toString()).build();
    return Response
        .created(uri)
        .entity(submissionMapper.toSubmissionDTO(result))
        .build();
  }

  @PUT
  @Path("/{submissionId:[0-9]+}")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Response updateSubmission(
      @PathParam("submissionId") Long submissionId,
      @FormParam("assignment") String submissionJson,
      @FormParam("file") EntityPart file,
      @Context UriInfo uriInfo
  ) {
    SubmissionDTO submissionDto = parseToSubmissionDto(submissionJson);
    Submission submission = submissionMapper.toSubmission(submissionDto);
    var submissionSpec = SubmissionSpec.builder()
        .submission(submission)
        .file(file.getContent())
        .fileName(file.getFileName().get())
        .build();
    Submission result = submissionService.updateSubmission(submissionId, submissionSpec);
    URI uri = uriInfo.getAbsolutePathBuilder().path(result.getId().toString()).build();
    return Response
        .created(uri)
        .entity(submissionMapper.toSubmissionDTO(result))
        .build();
  }

  @DELETE
  @Path("/{submissionId:[0-9]+}")
  public Response deleteSubmission(@PathParam("submissionId") Long submissionId) {
    submissionService.deleteSubmission(submissionId);
    return Response.noContent().build();
  }

  private SubmissionDTO parseToSubmissionDto(String submissionJson) {
    try (var builder = JsonbBuilder.create()) {
      return builder.fromJson(submissionJson, SubmissionDTO.class);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
