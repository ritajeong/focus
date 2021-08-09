import Vue from 'vue';
import Vuex from 'vuex';
import meetingRoom from './modules/meetingRoom.js';
import user from './modules/users.js';

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  getters: {},
  modules: {
    meetingRoom,
    user,
  },
});

export default store;
