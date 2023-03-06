package me.hikingcarrot7.moodleripoff.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assignments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Assignment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "assignment_id")
  private Long id;

  @Column
  private String title;

  @Column
  private String description;

  @Column(name = "due_date")
  private LocalDateTime dueDate;

  @Column(name = "lock_after_due_date")
  private boolean lockAfterDueDate = false;

  @Column
  private boolean completed = false;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @ToString.Exclude
  private Course course;

  @OneToMany(
      mappedBy = "assignment",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  @ToString.Exclude
  private List<Submission> submissions = new ArrayList<>();

  @PrePersist
  public void prePersist() {
    createdAt = LocalDateTime.now(ZoneOffset.UTC);
  }

  public boolean isDue() {
    return dueDate.isBefore(LocalDateTime.now(ZoneOffset.UTC));
  }

  public boolean isLocked() {
    return lockAfterDueDate && isDue();
  }

}
