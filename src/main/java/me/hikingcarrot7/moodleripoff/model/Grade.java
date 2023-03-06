package me.hikingcarrot7.moodleripoff.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "grades")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Grade {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "grade_id")
  private Long id;

  @Column
  private double score;

  @Column
  private String feedback;

  @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "submission_id", referencedColumnName = "submission_id")
  @ToString.Exclude
  private Submission submission;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
  @ToString.Exclude
  private Teacher teacher;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
      return false;
    Grade grade = (Grade) o;
    return getId() != null && Objects.equals(getId(), grade.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

}
