package me.hikingcarrot7.moodleripoff.web.dto.mapper;

import me.hikingcarrot7.moodleripoff.model.Submission;
import me.hikingcarrot7.moodleripoff.web.dto.SubmissionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface SubmissionMapper {

  Submission toSubmission(SubmissionDTO submissionDTO);

  SubmissionDTO toSubmissionDTO(Submission submission);

}
