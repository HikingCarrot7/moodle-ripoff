package me.hikingcarrot7.moodleripoff.web.dto.mapper;

import me.hikingcarrot7.moodleripoff.model.Assignment;
import me.hikingcarrot7.moodleripoff.web.dto.AssignmentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta", uses = CourseMapper.class)
public interface AssignmentMapper {

  Assignment toAssignment(AssignmentDTO assignmentDTO);

  AssignmentDTO toAssignmentDto(Assignment assignment);

  List<AssignmentDTO> toAssignmentDtoList(List<Assignment> assignments);

}
