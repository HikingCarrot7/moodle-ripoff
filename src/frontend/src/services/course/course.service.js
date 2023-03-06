import api from '@/config/axios';

export const getAllCourses = async () => {
  const { data } = await api.get('/courses');
  return data;
};

export const getCoursesByStudentId = async (studentId) => {
  const { data } = await api.get(`/courses/student/${studentId}`);
  return data;
};

export const getCourseById = async (courseId) => {
  const { data } = await api.get(`/courses/${courseId}`);
  return data;
};

export const createCourse = async (course) => {
  const { data } = await api.post('/courses?teacherId=1', course);
  return data;
};

export const updateCourse = async (course) => {
  const { data } = await api.put(`/courses/${course.id}?teacherId=1`, course);
  return data;
};

export const deleteCourse = async (courseId) => {
  const { data } = await api.delete(`/courses/${courseId}`);
  return data;
};
