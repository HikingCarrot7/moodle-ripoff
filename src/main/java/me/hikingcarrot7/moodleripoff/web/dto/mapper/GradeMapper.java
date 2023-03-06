package me.hikingcarrot7.moodleripoff.web.dto.mapper;

import me.hikingcarrot7.moodleripoff.model.Grade;
import me.hikingcarrot7.moodleripoff.web.dto.GradeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface GradeMapper {

  Grade toGrade(GradeDTO gradeDto);

  GradeDTO toGradeDto(Grade grade);

}
