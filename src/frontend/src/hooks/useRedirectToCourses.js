import { useRouter } from 'next/router';
import { useAuth } from '@/hooks/useAuth';
import { useEffect } from 'react';

export const useRedirectToCourses = () => {
  const router = useRouter();
  const { userLogged } = useAuth();

  useEffect(() => {
    if (userLogged()) {
      router.push('/courses');
    }
  }, [userLogged]);
};
