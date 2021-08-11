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
  //ㅇㅇ 아이디 중복확인 수정후 다시 작업
  return instance.get(input);
}
function addUser(input) {
  //ㅇㅇ 아이디 중복확인 수정후 다시 작업
  return instance.get('add/' + input);
}
export { registerUser, loginUser, checkUser, addUser };
