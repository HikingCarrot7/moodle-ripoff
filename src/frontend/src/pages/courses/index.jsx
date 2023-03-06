import { getAllCourses } from '@/services/course/course.service';
import { CourseCardGrid } from '@/components/courses/CourseCardGrid';
import Link from 'next/link';

const index = ({ courses }) => {
  return (
    <div>
      <Link href="/courses/create">Create course</Link>
      <CourseCardGrid courses={courses} />;
    </div>
  );
};

export const getServerSideProps = async () => {
  const courses = await getAllCourses();
  return {
    props: {
      courses,
    },
  };
};

export default index;
