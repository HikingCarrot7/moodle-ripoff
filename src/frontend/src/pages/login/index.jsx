import { LoginForm } from '@/components/auth/LoginForm';
import { TabPanel, TabView } from 'primereact/tabview';
import { useRouter } from 'next/router';
import { loginStudent, loginTeacher } from '@/services/auth/auth.service';

const LoginPage = () => {
  const router = useRouter();

  const onLoginStudent = async (studentLoginRequest) => {
    await loginStudent(studentLoginRequest);
    await router.push('/courses');
  };

  const onLoginTeacher = async (teacherLoginRequest) => {
    await loginTeacher(teacherLoginRequest);
    await router.push('/courses');
  };

  const onGoToRegister = () => {
    router.push('/register');
  };

  return (
    <div className="grid">
      <div className="col-6 m-auto">
        <TabView>
          <TabPanel header="I'm an student">
            <LoginForm
              title="Welcome back student"
              onSubmit={onLoginStudent}
              onGoToRegister={onGoToRegister}
            />
          </TabPanel>
          <TabPanel header="I'm a teacher">
            <LoginForm
              title="Welcome back teacher"
              onSubmit={onLoginTeacher}
              onGoToRegister={onGoToRegister}
            />
          </TabPanel>
        </TabView>
      </div>
    </div>
  );
};

export default LoginPage;
