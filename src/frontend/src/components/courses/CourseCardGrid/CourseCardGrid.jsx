import { CourseCard } from '@/components/courses/CourseCard';

export const CourseCardGrid = ({ courses }) => {
  if (!courses) {
    return <div>No courses found</div>;
  }

  return (
    <div className="grid grid-nogutter gap-5 my-auto">
      {courses.map((course, index) => (
        <CourseCard key={index} course={course} />
      ))}
    </div>
  );
};
