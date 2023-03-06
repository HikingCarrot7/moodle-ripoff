package me.hikingcarrot7.moodleripoff.security.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import me.hikingcarrot7.moodleripoff.model.Student;
import me.hikingcarrot7.moodleripoff.model.Teacher;
import me.hikingcarrot7.moodleripoff.security.JwtTokenGenerator;
import me.hikingcarrot7.moodleripoff.security.SecurityUser;
import me.hikingcarrot7.moodleripoff.service.StudentService;
import me.hikingcarrot7.moodleripoff.service.TeacherService;
import me.hikingcarrot7.moodleripoff.util.PasswordEncoder;
import me.hikingcarrot7.moodleripoff.web.dto.auth.LoginRequestDTO;
import me.hikingcarrot7.moodleripoff.web.dto.auth.TokenDTO;

@ApplicationScoped
public class LoginAndRegistrationService {
  @Inject private JwtTokenGenerator jwtTokenGenerator;
  @Inject private PasswordEncoder passwordEncoder;
  @Inject private StudentService studentService;
  @Inject private TeacherService teacherService;

  public TokenDTO loginStudent(LoginRequestDTO loginRequest) {
    String email = loginRequest.getEmail();
    Student student = studentService.getStudentByEmail(email);
    if (passwordEncoder.matches(loginRequest.getPassword(), student.getPassword())) {
      return generateTokenForStudent(student);
    }
    throw new RuntimeException("Invalid password");
  }

  public TokenDTO loginTeacher(LoginRequestDTO loginRequest) {
    String email = loginRequest.getEmail();
    Teacher teacher = teacherService.getTeacherByEmail(email);
    if (passwordEncoder.matches(loginRequest.getPassword(), teacher.getPassword())) {
      return generateTokenForTeacher(teacher);
    }
    throw new RuntimeException("Invalid password");
  }

  public TokenDTO registerStudent(Student student) {
    System.out.println(student.getPassword());
    Student savedStudent = studentService.createStudent(student);
    return generateTokenForStudent(savedStudent);
  }

  public TokenDTO registerTeacher(Teacher teacher) {
    Teacher savedTeacher = teacherService.createTeacher(teacher);
    return generateTokenForTeacher(savedTeacher);
  }

  public TokenDTO generateTokenForStudent(Student student) {
    SecurityUser securityUser = SecurityUser.builder()
        .id(student.getId())
        .email(student.getEmail())
        .role("student")
        .build();
    String jwtAccessToken = jwtTokenGenerator.generateJWTString(securityUser);
    return TokenDTO.builder()
        .id(student.getId())
        .email(student.getEmail())
        .accessToken(jwtAccessToken)
        .build();
  }

  private TokenDTO generateTokenForTeacher(Teacher teacher) {
    SecurityUser securityUser = SecurityUser.builder()
        .id(teacher.getId())
        .email(teacher.getEmail())
        .role("teacher")
        .build();
    String jwtAccessToken = jwtTokenGenerator.generateJWTString(securityUser);
    return TokenDTO.builder()
        .id(teacher.getId())
        .email(teacher.getEmail())
        .accessToken(jwtAccessToken)
        .build();
  }

}
