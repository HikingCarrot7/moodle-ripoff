import { AssignmentForm } from '@/components/assignments/AssignmentForm';
import { convertToyyyyMMddHHmmss } from '@/utils/date';
import { createAssignment } from '@/services/assignment/assignment.service';
import { useRouter } from 'next/router';

const CreateAssignmentPage = ({ courseId }) => {
  const router = useRouter();

  const onSubmit = async (newAssignment) => {
    newAssignment.dueDate = convertToyyyyMMddHHmmss(newAssignment.dueDate);
    const result = await createAssignment(courseId, newAssignment);
    router.push(`/assignments/${result.id}`);
  };

  return (
    <div>
      <h1>Create Assignment</h1>
      <AssignmentForm onSubmit={onSubmit} />
    </div>
  );
};

export const getServerSideProps = async (context) => {
  const { courseId } = context.query;

  return {
    props: {
      courseId,
    },
  };
};

export default CreateAssignmentPage;
