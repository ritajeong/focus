import Vue from "vue";
import Vuex from "vuex";
import meetingRoom from './modules/meetingRoom.js'

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    meetingRoom,
  },
});

export default store