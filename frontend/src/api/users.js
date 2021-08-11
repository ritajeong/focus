import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8446/users/',
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

function checkUser(input) {
  return instance.get('check/' + input);
}

// function addUser(input) {
//   return instance.get('add/' + input);
// }

export { registerUser, loginUser, checkUser };
