package me.hikingcarrot7.moodleripoff.web.dto.mapper;

import me.hikingcarrot7.moodleripoff.model.Teacher;
import me.hikingcarrot7.moodleripoff.web.dto.TeacherDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface TeacherMapper {

  Teacher toTeacher(TeacherDTO teacherDto);

  TeacherDTO toTeacherDto(Teacher teacher);

}
