import { useEffect } from 'react';
import { useRouter } from 'next/router';

export const useRemoveQueryParams = (path) => {
  const router = useRouter();

  useEffect(() => {
    router.replace(path, undefined, { shallow: true });
  }, [path]);
};
