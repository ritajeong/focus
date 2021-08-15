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
function showRooms(input) {
  return instance.get('room/' + input);
}
function showRoomdetail(ShowFileReq) {
  return instance.get('detail', ShowFileReq);
}
function deletefile(FileReq) {
  return instance.post('deletefile', FileReq);
}

function updatefile(FileReq) {
  return instance.post('updatefile', FileReq);
}

export { downloadFile, showRooms, showRoomdetail, deletefile, updatefile };
