import { getRooms, getRoom } from '@/api/rooms.js';
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
      let now = new Date().toISOString().slice(0, 19);
      state.now = [];
      state.future = [];
      state.history = [];
      for (let data of payload.data) {
        if (data.startTime > now) {
          state.future.push(data);
        } else if (data.endTime < now) {
          state.history.push(data);
        } else {
          state.now.push(data);
        }
      }
    },
    SET_ROOM(state, payload) {
      state.room = payload;
    },
  },
  actions: {
    async fetchRooms({ commit }) {
      commit('SET_ROOMS', await getRooms());
    },
    async fetchRoom({ commit }, roomId) {
      commit('SET_ROOM', await getRoom(roomId));
    },
  },
  getters: {
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
