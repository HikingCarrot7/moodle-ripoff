package me.hikingcarrot7.moodleripoff.web.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.json.JsonNumber;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import me.hikingcarrot7.moodleripoff.model.Submission;
import me.hikingcarrot7.moodleripoff.model.spec.SubmissionSpec;
import me.hikingcarrot7.moodleripoff.service.SubmissionService;
import me.hikingcarrot7.moodleripoff.web.dto.SubmissionDTO;
import me.hikingcarrot7.moodleripoff.web.dto.mapper.SubmissionMapper;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.net.URI;
import java.util.Optional;

@Path("/submissions")
@RolesAllowed({"student", "teacher"})
@Produces(MediaType.APPLICATION_JSON)
public class SubmissionController {
  @Inject private JsonWebToken principal;
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

  @GET
  public Response getSubmissionOfAssignmentByStudentId(
      @QueryParam("studentId") Long studentId,
      @QueryParam("assignmentId") Long assignmentId
  ) {
    Optional<Submission> result = submissionService.getSubmissionOfAssignmentByStudentId(assignmentId, studentId);
    if (result.isPresent()) {
      return Response
          .ok(submissionMapper.toSubmissionDTO(result.get()))
          .build();
    } else {
      return Response.noContent().build();
    }
  }

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @RolesAllowed("student")
  public Response submitAssignment(
      @QueryParam("assignmentId") Long assignmentId,
      @FormParam("submission") String submissionJson,
      @FormParam("file") EntityPart file,
      @Context UriInfo uriInfo
  ) {
    Long studentId = getLoggedStudentId();
    SubmissionDTO submissionDto = parseToSubmissionDto(submissionJson);
    Submission submission = submissionMapper.toSubmission(submissionDto);
    var submissionSpec = SubmissionSpec.builder()
        .submission(submission)
        .studentId(studentId)
        .assignmentId(assignmentId)
        .file(file)
        .build();
    Submission result = submissionService.createSubmission(submissionSpec);
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

  private Long getLoggedStudentId() {
    return ((JsonNumber) principal.getClaim("id")).longValue();
  }

}
