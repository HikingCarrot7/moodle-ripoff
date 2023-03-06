import { useEffect, useState } from 'react';

export const useAuth = () => {
  const [login, setLogin] = useState(null);

  useEffect(() => {
    const login = getLogin();
    setLogin(login);
  }, []);

  const logout = () => {
    localStorage.removeItem('login');
  };

  const userLogged = () => {
    return login !== null;
  };

  const getToken = () => {
    return login?.token;
  };

  const getLoggedUserRole = () => {
    return login?.role;
  };

  const getLoggedUserName = () => {
    return login?.name;
  };

  const getLogin = () => {
    const login = JSON.parse(localStorage.getItem('login'));
    if (!login) {
      return null;
    }
    return login;
  };

  return {
    userLogged,
    getToken,
    getLoggedUserRole,
    getLoggedUserName,
  };
};
