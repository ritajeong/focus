import Vue from 'vue';
import Vuex from 'vuex';
import meetingRoom from './modules/meetingRoom.js';
import users from './modules/users.js';
import rooms from './modules/rooms.js';

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  getters: {},
  modules: {
    meetingRoom,
    users,
    rooms,
  },
});

export default store;
