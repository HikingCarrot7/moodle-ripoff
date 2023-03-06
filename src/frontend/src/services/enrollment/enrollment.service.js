import api from '@/config/axios';

export const getEnrollmentsOfCourse = async (courseId) => {
  const { data } = await api.get(`/enrollments?courseId=${courseId}`);
  return data;
};
