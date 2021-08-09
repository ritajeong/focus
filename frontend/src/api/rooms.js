import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8446/rooms/',
  headers: {
    'Content-type': 'application/json',
  },
  withCredentials: true,
});

function registerUser(userData) {
  return instance.post('register', userData);
}

function loginUser(userData) {
  return instance.post('login', userData);
}

export { registerUser, loginUser };
