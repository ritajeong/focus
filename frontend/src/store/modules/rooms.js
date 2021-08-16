import { getRooms, getRoom } from '@/api/rooms.js';
import moment from 'moment';
export default {
  namespaced: true,
  state: () => ({
    rooms: [],
    now: [],
    future: [],
    history: [],
    room: {},
  }),
  mutations: {
    SET_ROOMS(state, payload) {
      state.rooms = payload;
      let now = moment(new Date()).format('YYYY-MM-DD HH:MM');
      state.now = [];
      state.future = [];
      state.history = [];
      for (let data of payload.data) {
        if (moment(data.startTime).format('YYYY-MM-DD HH:MM') > now) {
          console.log(data.room_id + ' ' + data.name + ' ' + data.startTime);
          state.future.push(data);
        } else if (data.endTime) {
          console.log(data.room_id + ' ' + data.name + ' ' + data.endTime);
          state.history.push(data);
        } else {
          console.log(data.room_id + ' ' + data.name + ' ' + data.startTime);
          state.now.push(data);
        }
      }
    },
    SET_ROOM(state, payload) {
      state.room = payload;
    },
  },
  actions: {
    async fetchRooms({ commit }, userid) {
      commit('SET_ROOMS', await getRooms(userid));
    },
    async fetchRoom({ commit }, roomId) {
      commit('SET_ROOM', await getRoom(roomId));
    },
    setRoom({ commit }, roomInfo) {
      commit('SET_ROOM', roomInfo);
    },
  },
  getters: {
    room(state) {
      console.log('getters room');
      return state.room;
    },
    arrayNow: state => {
      return state.now;
    },
    arrayFuture: state => {
      return state.future;
    },
    arrayHistory: state => {
      return state.history;
    },
    lengthNow: state => {
      return state.now.length;
    },
    lengthFuture: state => {
      return state.future.length;
    },
    lengthHistory: state => {
      return state.history.length;
    },
  },
};
