package me.hikingcarrot7.moodleripoff.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "submissions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Submission implements WithCloudFile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "submission_id")
  private Long id;

  @Column
  private String description;

  @Embedded
  @ToString.Exclude
  private CloudFile file;

  @Column(name = "submitted_at")
  private LocalDateTime submittedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "assignment_id", referencedColumnName = "assignment_id")
  @ToString.Exclude
  private Assignment assignment;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id", referencedColumnName = "student_id")
  @ToString.Exclude
  private Student student;

  @OneToOne(fetch = FetchType.LAZY)
  @ToString.Exclude
  private Grade grade;

  public void setAssignment(Assignment assignment) {
    this.assignment = assignment;
    assignment.getSubmissions().add(this);
  }

  public void setStudent(Student student) {
    this.student = student;
    student.getSubmissions().add(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
      return false;
    Submission that = (Submission) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  @Override
  public boolean hasFile() {
    return nonNull(file) && file.isValidFile();
  }

}
