package me.hikingcarrot7.moodleripoff.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "course_id")
  private Long id;

  @Column
  private String name;

  @Column
  private String description;

  @Column
  private String code;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
  @ToString.Exclude
  private Teacher teacher;

  @OneToMany(
      mappedBy = "course",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  @ToString.Exclude
  private List<Enrollment> enrollments = new ArrayList<>();

  @OneToMany(
      mappedBy = "course",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  @ToString.Exclude
  private List<Assignment> assignments;

  public void enrollStudent(Student student) {
    Enrollment enrollment = new Enrollment(student, this);
    enrollments.add(enrollment);
    student.getEnrollments().add(enrollment);
  }

  public void removeEnrollmentOf(Student student) {
    for (Iterator<Enrollment> iterator = enrollments.iterator(); iterator.hasNext(); ) {
      Enrollment enrollment = iterator.next();
      if (enrollment.getCourse().equals(this) && enrollment.getStudent().equals(student)) {
        iterator.remove();
        enrollment.getStudent().getEnrollments().remove(enrollment);
        enrollment.setStudent(null);
        enrollment.setCourse(null);
      }
    }
  }

  public boolean isStudentEnrolled(Student student) {
    for (Enrollment enrollment : enrollments) {
      if (enrollment.getCourse().equals(this) && enrollment.getStudent().equals(student)) {
        return true;
      }
    }
    return false;
  }

  public void addAssignment(Assignment assignment) {
    assignments.add(assignment);
    assignment.setCourse(this);
  }

  public void removeAssignment(Assignment assignment) {
    assignments.remove(assignment);
    assignment.setCourse(null);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Course)) return false;
    return id != null && id.equals(((Course) o).getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

}
