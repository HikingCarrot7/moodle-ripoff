import { api } from '@/config/axios';

export const loginStudent = async (loginRequest) => {
  const { data: loginResult } = await api.post(
    '/auth/login/students',
    loginRequest,
  );
  saveStudentLoginResult(loginResult);
};

export const loginTeacher = async (loginRequest) => {
  const { data: loginResult } = await api.post(
    '/auth/login/teachers',
    loginRequest,
  );
  saveTeacherLoginResult(loginResult);
};

export const registerStudent = async (registerRequest) => {
  const { data: registerResult } = await api.post(
    '/auth/register/students',
    registerRequest,
  );
  saveStudentLoginResult(registerResult);
};

export const registerTeacher = async (registerRequest) => {
  const { data: registerResult } = await api.post(
    '/auth/register/teachers',
    registerRequest,
  );
  saveTeacherLoginResult(registerResult);
};

const saveStudentLoginResult = (login) => {
  localStorage.setItem('login', JSON.stringify({ ...login, role: 'student' }));
};

const saveTeacherLoginResult = (login) => {
  localStorage.setItem('login', JSON.stringify({ ...login, role: 'teacher' }));
};

export const getToken = () => {
  const login = JSON.parse(localStorage.getItem('login'));
  return login?.accessToken;
};
