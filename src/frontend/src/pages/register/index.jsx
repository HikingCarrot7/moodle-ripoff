import { TabPanel, TabView } from 'primereact/tabview';
import { RegisterForm } from '@/components/auth/RegisterForm';
import { useRouter } from 'next/router';
import { registerStudent, registerTeacher } from '@/services/auth/auth.service';

const RegisterPage = () => {
  const router = useRouter();

  const onRegisterStudent = async (registerStudentRequest) => {
    await registerStudent(registerStudentRequest);
    await router.push('/courses');
  };

  const onRegisterTeacher = async (registerTeacherRequest) => {
    await registerTeacher(registerTeacherRequest);
    await router.push('/courses');
  };

  const onGoToLogin = () => {
    router.push('/login');
  };

  return (
    <div className="grid">
      <div className="col-6 m-auto">
        <TabView>
          <TabPanel header="I'm an student">
            <RegisterForm
              title="Register as an student"
              onSubmit={onRegisterStudent}
              onGoToLogin={onGoToLogin}
            />
          </TabPanel>
          <TabPanel header="I'm a teacher">
            <RegisterForm
              title="Register as a teacher"
              onSubmit={onRegisterTeacher}
              onGoToLogin={onGoToLogin}
            />
          </TabPanel>
        </TabView>
      </div>
    </div>
  );
};

export default RegisterPage;
