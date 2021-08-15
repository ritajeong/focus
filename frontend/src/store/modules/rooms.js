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
      for (let date of payload.data) {
        if (date.startTime > now) {
          state.future.push(date);
        } else if (date.endTime < now) {
          state.history.push(date);
        } else {
          state.now.push(date);
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
    setRoom({ commit }, roomInfo) {
      commit('SET_ROOM', roomInfo);
    },
  },
  getters: {},
};
