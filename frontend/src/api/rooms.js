import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8446/rooms/',
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

function getRooms() {
  return instance.get();
}

function getRoom(roomId) {
  return instance.get(roomId);
}

export { createRoom, getRooms, getRoom, updateRoom };
