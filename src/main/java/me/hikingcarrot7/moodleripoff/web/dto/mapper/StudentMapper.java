package me.hikingcarrot7.moodleripoff.web.dto.mapper;

import me.hikingcarrot7.moodleripoff.model.Student;
import me.hikingcarrot7.moodleripoff.web.dto.StudentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta")
public interface StudentMapper {

  Student toStudent(StudentDTO studentDTO);

  StudentDTO toStudentDto(Student student);

  List<StudentDTO> toStudentDtoList(List<Student> students);

}

