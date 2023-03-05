package me.hikingcarrot7.moodleripoff.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import me.hikingcarrot7.moodleripoff.model.Assignment;
import me.hikingcarrot7.moodleripoff.model.CloudFile;
import me.hikingcarrot7.moodleripoff.model.Student;
import me.hikingcarrot7.moodleripoff.model.Submission;
import me.hikingcarrot7.moodleripoff.model.spec.SubmissionSpec;
import me.hikingcarrot7.moodleripoff.repository.SubmissionRepository;
import me.hikingcarrot7.moodleripoff.service.exception.SubmissionNotFoundException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@ApplicationScoped
public class SubmissionService {
  @Inject private FileUploaderService fileUploaderService;
  @Inject private SubmissionRepository submissionRepository;
  @Inject private StudentService studentService;
  @Inject private AssignmentService assignmentService;

  public Submission getSubmissionById(Long id) {
    return submissionRepository
        .getSubmissionById(id)
        .orElseThrow(() -> new SubmissionNotFoundException(id));
  }

  @Transactional
  public Submission createSubmission(SubmissionSpec submissionSpec) {
    Submission newSubmission = submissionSpec.getSubmission();
    Student student = studentService.getStudentById(submissionSpec.getStudentId());
    Assignment assignment = assignmentService.getAssignmentById(submissionSpec.getAssignmentId());

    newSubmission.setStudent(student);
    newSubmission.setAssignment(assignment);
    newSubmission.setSubmittedAt(LocalDateTime.now(ZoneOffset.UTC));
    if (submissionSpec.hasFile()) {
      CloudFile file = fileUploaderService.uploadFile(submissionSpec.getFileBytes());
      newSubmission.setFile(file);
    }

    return submissionRepository.saveSubmission(newSubmission);
  }

  @Transactional
  public Submission updateSubmission(Long submissionId, SubmissionSpec submissionSpec) {
    Submission submissionToUpdate = getSubmissionById(submissionId);
    Submission updatedSubmission = submissionSpec.getSubmission();

    if (submissionSpec.hasFile()) {
      CloudFile file = fileUploaderService.uploadOrUpdateFile(submissionToUpdate, submissionSpec.getFileBytes());
      submissionToUpdate.setFile(file);
    }
    submissionToUpdate.setDescription(updatedSubmission.getDescription());

    return submissionRepository.saveSubmission(submissionToUpdate);
  }

  @Transactional
  public void deleteSubmission(Long submissionId) {
    Submission submission = getSubmissionById(submissionId);
    fileUploaderService.deleteFile(submission.getFile());
    submissionRepository.deleteSubmission(submission);
  }

}
