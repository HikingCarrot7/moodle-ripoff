package me.hikingcarrot7.moodleripoff.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "students")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@AttributeOverride(name = "id", column = @Column(name = "student_id"))
public class Student extends Person {
  @OneToMany(
      mappedBy = "student",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  @ToString.Exclude
  private List<Enrollment> enrollments = new ArrayList<>();

  @OneToMany(
      mappedBy = "student",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  @ToString.Exclude
  private List<Submission> submissions = new ArrayList<>();

  @OneToMany(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE}
  )
  private List<Assignment> completedAssignments = new ArrayList<>();

  public boolean hasCompletedAssignment(Assignment assignment) {
    return completedAssignments.contains(assignment);
  }

  public void addCompletedAssignment(Assignment assignment) {
    completedAssignments.add(assignment);
  }

  public void removeCompletedAssignment(Assignment assignment) {
    completedAssignments.remove(assignment);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return Objects.equals(getId(), student.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

}
