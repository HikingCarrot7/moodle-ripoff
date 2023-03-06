import { getCourseById, updateCourse } from '@/services/course/course.service';
import { CourseForm } from '@/components/courses/CourseForm/CourseForm';
import { useRouter } from 'next/router';
import { useRemoveQueryParams } from '@/hooks/useRemoveQueryParams';

const UpdateCourse = ({ course }) => {
  const router = useRouter();
  useRemoveQueryParams('/courses/update');

  const onSubmit = async (updatedCourse) => {
    await updateCourse({ ...updatedCourse, id: course.id });
    await router.push('/courses');
  };

  return (
    <div>
      <h1>Update Course {course.id}</h1>
      <CourseForm course={course} onSubmit={onSubmit} />
    </div>
  );
};

export async function getServerSideProps(context) {
  const { courseId } = context.query;
  const course = await getCourseById(courseId);

  return {
    props: {
      course,
    },
  };
}

export default UpdateCourse;
