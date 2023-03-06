package me.hikingcarrot7.moodleripoff.web.dto.mapper;

import me.hikingcarrot7.moodleripoff.model.Teacher;
import me.hikingcarrot7.moodleripoff.web.dto.TeacherDTO;
import me.hikingcarrot7.moodleripoff.web.dto.teacher.UpdateTeacherRequestDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta", builder = @Builder(disableBuilder = true))
public interface TeacherMapper {

  Teacher toTeacher(TeacherDTO teacherDto);

  Teacher toTeacher(UpdateTeacherRequestDTO updateTeacherRequestDTO);

  TeacherDTO toTeacherDto(Teacher teacher);

}
