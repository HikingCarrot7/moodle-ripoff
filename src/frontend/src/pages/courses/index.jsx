import { CourseCardGrid } from '@/components/courses/CourseCardGrid';
import Link from 'next/link';
import { useEffect, useState } from 'react';
import { getAllCourses } from '@/services/course/course.service';
import { useAuth } from '@/hooks/useAuth';

const index = () => {
  const [courses, setCourses] = useState(null);
  const { getLoggedUserRole, getLoggedUserName } = useAuth();

  useEffect(() => {
    const getCourses = async () => {
      const courses = await getAllCourses();
      setCourses(courses);
    };
    getCourses();
  }, []);

  return (
    <div>
      {getLoggedUserRole() === 'teacher' && (
        <Link href="/courses/create">Create course</Link>
      )}
      <h1>
        Welcome back{' '}
        <span className={'text-primary'}>{getLoggedUserName()}</span>
      </h1>
      <h2>
        You're currently logged in as{' '}
        <span className={'text-blue-500'}>
          {getLoggedUserRole()?.toUpperCase()}
        </span>
      </h2>
      <h2>
        All available courses <i className="pi pi-arrow-down"></i>
      </h2>
      <CourseCardGrid courses={courses} />;
    </div>
  );
};

export default index;
