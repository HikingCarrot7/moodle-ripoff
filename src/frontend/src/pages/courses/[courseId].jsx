import { getCourseById } from '@/services/course/course.service';
import { getAssignmentsOfCourse } from '@/services/assignment/assignment.service';
import { AssignmentList } from '@/components/assignments/AssignmentList';
import { getEnrollmentsOfCourse } from '@/services/enrollment/enrollment.service';
import { EnrollmentList } from '@/components/enrollments/EnrollmentList';
import { Calendar } from '@/components/shared/Calendar';
import { useEffect, useState } from 'react';
import { useRouter } from 'next/router';
import { Button } from 'primereact/button';

const CoursePage = ({ courseId }) => {
  const router = useRouter();
  const [course, setCourse] = useState(null);
  const [assignments, setAssignments] = useState(null);
  const [enrollments, setEnrollments] = useState(null);

  useEffect(() => {
    getCourseById(courseId).then((course) => {
      setCourse(course);
      getAssignmentsOfCourse(courseId).then((assignments) => {
        setAssignments(assignments);
        getEnrollmentsOfCourse(courseId).then((enrollments) => {
          setEnrollments(enrollments);
        });
      });
    });
  }, [courseId]);

  const goToCreateAssignment = () => {
    router.push(`/assignments/create?courseId=${course.id}`);
  };

  if (!course || !assignments || !enrollments) {
    return <div>Loading...</div>;
  }

  return (
    <div className="-mt-3">
      <h2 className="text-4xl">
        Welcome to the <span className="text-primary">{course.name}</span>{' '}
        course
      </h2>
      <div className="grid -mt-5">
        <div className="col-5">
          <Calendar
            title="Upcoming activities for this course"
            assignments={assignments}
          />
          <EnrollmentList enrollments={enrollments} />
        </div>
        <div className="col">
          <div className="flex justify-content-end mb-3">
            <Button
              label="Create assignment"
              severity="secondary"
              onClick={goToCreateAssignment}
            />
          </div>
          <AssignmentList assignments={assignments} />
        </div>
      </div>
    </div>
  );
};

export async function getServerSideProps(context) {
  const { courseId } = context.params;
  return {
    props: {
      courseId,
    },
  };
}

export default CoursePage;
