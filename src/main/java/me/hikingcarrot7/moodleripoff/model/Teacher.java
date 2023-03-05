package me.hikingcarrot7.moodleripoff.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "teacher_id"))
public class Teacher extends Person {
  @OneToMany(
      mappedBy = "teacher",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private List<Course> ownedCourses;

  public void addCourse(Course course) {
    ownedCourses.add(course);
    course.setTeacher(this);
  }

  public void removeCourse(Course course) {
    ownedCourses.remove(course);
    course.setTeacher(null);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
      return false;
    Teacher teacher = (Teacher) o;
    return getId() != null && Objects.equals(getId(), teacher.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

}
