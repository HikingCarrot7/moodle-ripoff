package me.hikingcarrot7.moodleripoff.model;

import jakarta.persistence.*;
import lombok.*;

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

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "submission_id", referencedColumnName = "submission_id")
  @ToString.Exclude
  private Submission submission;
}
