package me.hikingcarrot7.moodleripoff.web.dto.mapper;

import me.hikingcarrot7.moodleripoff.model.Enrollment;
import me.hikingcarrot7.moodleripoff.web.dto.EnrollmentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta", uses = StudentMapper.class)
public interface EnrollmentMapper {

  EnrollmentDTO toEnrollmentDTO(Enrollment enrollment);

  List<EnrollmentDTO> toEnrollmentDtoList(List<Enrollment> enrollments);

}
