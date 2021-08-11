import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8446/board/',
  headers: {
    'Content-type': 'application/json',
  },
  withCredentials: true,
});

function downloadFile(filereq) {
  return instance.post('down', filereq);
}

export { downloadFile };
