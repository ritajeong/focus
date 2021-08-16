import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://52.79.80.65:8446/rooms/',
  headers: {
    'Content-type': 'application/json',
  },
  withCredentials: true,
});

function createRoom(roomData) {
  return instance.post('createroom', roomData);
}

function getRooms() {
  return instance.get();
}

function getRoom(roomId) {
  return instance.get(roomId);
}

export { createRoom, getRooms, getRoom };
