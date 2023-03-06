import api from '@/config/axios';

export const getAssignmentsOfCourse = async (courseId) => {
  const { data } = await api.get(`/assignments/course/${courseId}`);
  return data;
};

export const getAssignmentsOfStudent = async (studentId) => {
  const { data } = await api.get(`/assignments/student/${studentId}`);
  return data;
};

export const getAssignmentById = async (assignmentId) => {
  const { data } = await api.get(`/assignments/${assignmentId}`);
  return data;
};

export const createAssignment = async (courseId, assignment) => {
  const { data } = await api.post(
    `/assignments?courseId=${courseId}`,
    assignment,
  );
  return data;
};

export const updateAssignment = async (assignmentId, assignment) => {
  const { data } = await api.put(`/assignments/${assignmentId}`, assignment);
  return data;
};
