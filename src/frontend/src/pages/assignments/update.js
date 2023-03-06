import { AssignmentForm } from '@/components/assignments/AssignmentForm';
import {
  getAssignmentById,
  updateAssignment,
} from '@/services/assignment/assignment.service';
import { useRemoveQueryParams } from '@/hooks/useRemoveQueryParams';
import { useRouter } from 'next/router';
import { Button } from 'primereact/button';
import { convertToyyyyMMddHHmmss } from '@/utils/date';

const UpdateAssignmentPage = ({ assignment }) => {
  const router = useRouter();
  useRemoveQueryParams('/assignments/update');

  const onSubmit = async (updatedAssignment) => {
    updatedAssignment.dueDate = convertToyyyyMMddHHmmss(
      updatedAssignment.dueDate,
    );
    await updateAssignment(assignment.id, updatedAssignment);
    router.push(`/assignments/${assignment.id}`);
  };

  return (
    <div>
      <h1>Update Assignment</h1>
      <Button onClick={() => router.back()} label="Go back" link />
      <AssignmentForm assignment={assignment} onSubmit={onSubmit} />
    </div>
  );
};

export const getServerSideProps = async (context) => {
  const { assignmentId } = context.query;
  const assignment = await getAssignmentById(assignmentId);

  return {
    props: {
      assignment,
    },
  };
};

export default UpdateAssignmentPage;
