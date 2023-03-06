import { getCourseById } from '@/services/course/course.service';
import { getAssignmentsOfCourse } from '@/services/assignment/assignment.service';
import { AssignmentList } from '@/components/assignments/AssignmentList';
import { getEnrollmentsOfCourse } from '@/services/enrollment/enrollment.service';
import { EnrollmentList } from '@/components/enrollments/EnrollmentList';
import { Calendar } from '@/components/shared/Calendar';
import Link from 'next/link';

const CoursePage = ({ course, assignments, enrollments }) => {
  return (
    <div>
      <h2>{course.name}</h2>
      <p>{course.description}</p>
      <div className="grid">
        <div className="col-4">
          <Calendar
            title="Upcoming activities for this course"
            assignments={assignments}
          />
          <EnrollmentList enrollments={enrollments} />
        </div>
        <div className="col">
          <Link href={`/assignments/create?courseId=${course.id}`}>
            Create assignment
          </Link>
          <AssignmentList assignments={assignments} />
        </div>
      </div>
    </div>
  );
};

export async function getServerSideProps(context) {
  const { courseId } = context.params;
  const course = await getCourseById(courseId);
  const assignments = await getAssignmentsOfCourse(courseId);
  const enrollments = await getEnrollmentsOfCourse(courseId);

  return {
    props: {
      course,
      assignments,
      enrollments,
    },
  };
}

export default CoursePage;
