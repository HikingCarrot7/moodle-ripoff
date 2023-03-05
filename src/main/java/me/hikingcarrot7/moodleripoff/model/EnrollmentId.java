package me.hikingcarrot7.moodleripoff.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
public class EnrollmentId implements Serializable {
  @Column(name = "student_id")
  private Long studentId;

  @Column(name = "course_id")
  private Long courseId;

  public EnrollmentId(Long studentId, Long courseId) {
    this.studentId = studentId;
    this.courseId = courseId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
      return false;
    EnrollmentId that = (EnrollmentId) o;
    return getStudentId() != null && Objects.equals(getStudentId(), that.getStudentId())
        && getCourseId() != null && Objects.equals(getCourseId(), that.getCourseId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentId, courseId);
  }

}
