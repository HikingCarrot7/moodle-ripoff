import { getCoursesByStudentId } from '@/services/course/course.service';
import { CourseCardGrid } from '@/components/courses/CourseCardGrid';
import { getAssignmentsOfStudent } from '@/services/assignment/assignment.service';
import { Calendar } from '@/components/shared/Calendar';

const HomePage = ({ enrolledCourses, assignments }) => {
  return (
    <div>
      <div className="grid">
        <div className="col-4">
          <Calendar title="Upcoming activities" assignments={assignments} />
        </div>
        <div className="col">
          <CourseCardGrid courses={enrolledCourses} />
        </div>
      </div>
    </div>
  );
};

export const getServerSideProps = async () => {
  const studentId = 1;
  const enrolledCourses = await getCoursesByStudentId(studentId);
  const assignments = await getAssignmentsOfStudent(studentId);

  return {
    props: {
      enrolledCourses,
      assignments,
    },
  };
};

export default HomePage;
