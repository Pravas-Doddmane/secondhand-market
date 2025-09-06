import React, { createContext, useState, useEffect, useContext } from 'react';
import api, { setAuthToken, removeAuthToken } from '../api/axios';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const loadUser = async () => {
      const token = localStorage.getItem('authToken');
      if (token) {
        try {
          const res = await api.get('/users/me');
          setUser(res.data);
        } catch (err) {
          console.error("Token is invalid or expired. Clearing local storage.");
          removeAuthToken();
          setUser(null);
        }
      }
      setLoading(false);
    };
    loadUser();
  }, []);

  const login = async (email, password) => {
    const res = await api.post('/auth/login', { email, password });
    setAuthToken(res.data.token);
    setUser(res.data);
  };

  const register = async (email, password, username) => {
    const res = await api.post('/auth/register', { email, password, username });
    return res.data;
  };

  const logout = async () => {
    try {
      await api.post('/auth/logout');
    } finally {
      removeAuthToken();
      setUser(null);
    }
  };

  return (
    <AuthContext.Provider value={{ user, loading, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);