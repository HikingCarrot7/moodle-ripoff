package me.hikingcarrot7.moodleripoff.web.dto.mapper;

import me.hikingcarrot7.moodleripoff.model.Course;
import me.hikingcarrot7.moodleripoff.web.dto.CourseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta")
public interface CourseMapper {

  Course toCourse(CourseDTO courseDto);

  CourseDTO toCourseDto(Course course);

  List<CourseDTO> toCourseDtoList(List<Course> courses);

}
