package me.hikingcarrot7.moodleripoff.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import me.hikingcarrot7.moodleripoff.model.Grade;
import me.hikingcarrot7.moodleripoff.model.Submission;
import me.hikingcarrot7.moodleripoff.repository.GradeRepository;
import me.hikingcarrot7.moodleripoff.service.exception.GradeNotFoundException;

@ApplicationScoped
public class GradeService {
  @Inject private GradeRepository gradeRepository;
  @Inject private SubmissionService submissionService;

  public Grade getGradeById(Long id) {
    return gradeRepository
        .findById(id)
        .orElseThrow(() -> new GradeNotFoundException(id));
  }

  public Grade createGrade(Long submissionId, Grade grade) {
    Submission submission = submissionService.getSubmissionById(submissionId);
    grade.setSubmission(submission);
    return gradeRepository.saveGrade(grade);
  }

  public Grade updateGrade(Long gradeId, Grade grade) {
    Grade gradeToUpdate = getGradeById(gradeId);
    gradeToUpdate.setScore(grade.getScore());
    gradeToUpdate.setFeedback(grade.getFeedback());
    return gradeRepository.updateGrade(gradeToUpdate);
  }

  public void deleteGrade(Long gradeId) {
    Grade grade = getGradeById(gradeId);
    gradeRepository.deleteGrade(grade);
  }

}
