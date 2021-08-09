import axios from 'axios';

// axios 객체 생성
export default axios.create({
  baseURL: 'http://localhost:8446',
  headers: {
    'Content-type': 'application/json',
  },
  withCredentials: true, // flase일 경우 jsessionid를 계속 바꿔서 전송함
});
