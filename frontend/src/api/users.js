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

function addUser(input) {
  return instance.get('add/' + input);
}

function findUser(email) {
  return instance.get(email);
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
  checkUser,
  addUser,
  findUser,
  searchUsers,
  updateUserName,
  updateUserPwd,
  deleteUser,
};
