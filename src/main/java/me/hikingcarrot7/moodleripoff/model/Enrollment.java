package me.hikingcarrot7.moodleripoff.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Enrollment {
  @EmbeddedId
  private EnrollmentId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("studentId")
  @ToString.Exclude
  private Student student;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("courseId")
  @ToString.Exclude
  private Course course;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  public Enrollment(Student student, Course course) {
    this.student = student;
    this.course = course;
    this.id = new EnrollmentId(student.getId(), course.getId());
  }

  @PrePersist
  private void init() {
    createdAt = LocalDateTime.now(ZoneOffset.UTC);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
      return false;
    Enrollment that = (Enrollment) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
