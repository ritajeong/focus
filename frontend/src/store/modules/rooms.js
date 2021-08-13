import { getRooms, getRoom } from '@/api/rooms.js';
export default {
  namespaced: true,
  state: () => ({
    rooms: {},
    room: {
      name: 'room1',
      description: 'description1',
      startTime: '2021-08-13T14:00:00',
      endTime: '',
      user_id: 1,
      room_id: 1,
    },
  }),
  mutations: {
    SET_ROOMS(state, payload) {
      state.rooms = payload;
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
    // isLogin(state) {
    //   return state.login.isLogin;
    // },
    // userInfo(state) {
    //   return state.login;
    // },
  },
};
