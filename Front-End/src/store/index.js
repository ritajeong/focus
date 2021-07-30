import Vue from "vue";
import Vuex from "vuex";
import meetingRoom from './modules/meetingRoom.js'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    meetingRoom,
  },
});
