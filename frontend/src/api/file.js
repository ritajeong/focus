import axios from 'axios';

const instance = axios.create({
  baseURL: 'https://i5a107.p.ssafy.io:8446/board/',
  // baseURL: 'https://localhost:8446/board/',
  headers: {
    'Content-type': 'application/json',
  },
  withCredentials: true,
});

function downloadFile(filereq) {
  return instance.post('down', filereq);
}
function showRooms(input) {
  return instance.get('room/' + input);
}
function showfiledetail(ShowFileReq) {
  return instance.post('detail', ShowFileReq);
}
function deletefile(FileReq) {
  return instance.post('deletefile', FileReq);
}
function deletetot(ShowFileReq) {
  return instance.post('delete', ShowFileReq);
}

function updatefile(FileReq) {
  return instance.post('updatefile', FileReq);
}

export {
  downloadFile,
  showRooms,
  deletetot,
  showfiledetail,
  deletefile,
  updatefile,
};
