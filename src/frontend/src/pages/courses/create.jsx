import { CourseForm } from '@/components/courses/CourseForm/CourseForm';
import { createCourse } from '@/services/course/course.service';
import { useRouter } from 'next/router';

const CreateCourse = () => {
  const router = useRouter();

  const onSubmit = async (newCourse) => {
    await createCourse(newCourse);
    await router.push('/courses');
  };

  return (
    <div>
      <h1>Create Course</h1>
      <CourseForm onSubmit={onSubmit} />
    </div>
  );
};

export default CreateCourse;
