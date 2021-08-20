import axios from 'axios';

const instance = axios.create({
  baseURL: 'https://i5a107.p.ssafy.io:8446/rooms/',
  //baseURL: 'https://localhost:8446/rooms/',
  headers: {
    'Content-type': 'application/json',
  },
  withCredentials: true,
});

function createRoom(roomData) {
  return instance.post('create', roomData);
}

function updateRoom(roomData) {
  return instance.put('update/' + roomData.room_id, roomData);
}

function deleteRoom(roomId) {
  return instance.delete('delete/' + roomId);
}

function getRooms(userid) {
  return instance.get('user/' + userid);
}

function getRoom(roomId) {
  return instance.get(roomId);
}

function getRoomIsOnLive(roomId) {
  return instance.get('onlive/' + roomId);
}

function setRoomOnLive(roomData) {
  return instance.put('onlive/', roomData);
}

export {
  createRoom,
  getRooms,
  getRoom,
  updateRoom,
  deleteRoom,
  getRoomIsOnLive,
  setRoomOnLive,
};
