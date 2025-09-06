import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
});

export const getAuthToken = () => {
  return localStorage.getItem('authToken');
};

export const setAuthToken = (token) => {
  localStorage.setItem('authToken', token);
};

export const removeAuthToken = () => {
  localStorage.removeItem('authToken');
};

api.interceptors.request.use((config) => {
  const token = getAuthToken();
  if (token) {
    config.headers['X-Auth-Token'] = token;
  }
  return config;
}, (error) => {
  return Promise.reject(error);
});

export default api;