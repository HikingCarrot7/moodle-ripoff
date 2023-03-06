import { getAssignmentById } from '@/services/assignment/assignment.service';
import Link from 'next/link';
import { SubmissionForm } from '@/components/submissions/SubmissionForm';
import { getSubmissionByAssignmentAndStudentId } from '@/services/submission/submission.service';
import api from '@/config/axios';

const AssignmentPage = ({ assignment, submission }) => {
  const onSubmit = async (submission) => {
    const file = submission.file[0];
    delete submission.file;

    const blob = new Blob([JSON.stringify(submission)], {
      type: 'application/json',
    });

    const formData = new FormData();
    formData.set('submission', blob);
    formData.set('file', file);

    await api.post(
      `/submissions?studentId=${1}&assignmentId=${assignment.id}`,
      formData,
      {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      },
    );
  };

  return (
    <div>
      <Link href={`/courses/${assignment.course.id}`}>Return to course</Link>
      <SubmissionForm submission={submission} onSubmit={onSubmit} />
    </div>
  );
};

export const getServerSideProps = async (context) => {
  const { assignmentId } = context.params;
  const studentId = 1;
  const assignment = await getAssignmentById(assignmentId);
  let submission = await getSubmissionByAssignmentAndStudentId(
    assignmentId,
    studentId,
  );

  if (!submission) {
    submission = null;
  }

  return {
    props: {
      assignment,
      submission,
    },
  };
};

export default AssignmentPage;
