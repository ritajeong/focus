import axios from 'axios';

const instance = axios.create({
  baseURL: 'https://i5a107.p.ssafy.io:8446/users/',
  //baseURL: 'https://localhost:8446/users/',
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

function findUser(email) {
  return instance.get(email);
}

function checkUser(email) {
  return instance.get('check/' + email);
}
function searchUsers(input) {
  return instance.get('search/' + input);
}
function updateUserName(userData) {
  return instance.put('update/name', userData);
}
function updateUserPwd(userData) {
  return instance.put('update/password', userData);
}

function deleteUser(userId) {
  return instance.delete('delete/' + userId);
}

export {
  registerUser,
  loginUser,
  findUser,
  checkUser,
  searchUsers,
  updateUserName,
  updateUserPwd,
  deleteUser,
};
