package me.hikingcarrot7.moodleripoff.web.dto.mapper;

import me.hikingcarrot7.moodleripoff.model.Student;
import me.hikingcarrot7.moodleripoff.web.dto.student.StudentDTO;
import me.hikingcarrot7.moodleripoff.web.dto.student.UpdateStudentRequestDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta", builder = @Builder(disableBuilder = true))
public interface StudentMapper {

  Student toStudent(StudentDTO studentDTO);

  Student toStudent(UpdateStudentRequestDTO updateStudentRequestDTO);

  StudentDTO toStudentDto(Student student);

}

