import api from '@/config/axios';

export const getSubmissionByAssignmentAndStudentId = async (
  assignmentId,
  studentId,
) => {
  const { data } = await api.get(
    `/submissions?assignmentId=${assignmentId}&studentId=${studentId}`,
  );
  return data;
};

export const uploadSubmission = async (submission, assignmentId) => {
  const file = submission.file[0];
  delete submission.file;

  const blob = new Blob([JSON.stringify(submission)], {
    type: 'application/json',
  });

  const formData = new FormData();
  formData.set('submission', blob);
  formData.set('file', file);

  const { data } = await api.post(
    `/submissions?studentId=${1}&assignmentId=${assignmentId}`,
    formData,
    {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    },
  );

  return data;
};
