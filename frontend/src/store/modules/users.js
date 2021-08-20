// import Vue from 'vue';
import { searchUsers } from '@/api/users.js';
// import router from '@/router/index.js';
import router from '../../router';
export default {
  namespaced: true,
  state: () => ({
    test: 'test',
    login: {
      isLogin: false,
      userid: '',
      useremail: '',
      username: '',
      //userpwd: '',
    },
    users: [], // 전체 사용자
  }),
  mutations: {
    SET_LOGIN(state, payload) {
      // Vue.set(state.login, 'isLogin', true);
      // console.log('login: ', payload);
      state.login.isLogin = true;
      state.login.userid = payload.id;
      state.login.username = payload.name;
      state.login.useremail = payload.email;
      //state.login.userpwd = payload.password;
      router.push('/dashboard');
    },
    SET_LOGOUT(state) {
      state.login.isLogin = false;
      state.login.userid = '';
      state.login.username = '';
      state.login.useremail = '';
      //state.login.userpwd = '';
      // console.log('SET_LOGOUT');
      router.push('/');
    },
    SET_ALL_USERS(state, payload) {
      state.users = payload;
    },
    SET_NAME(state, payload) {
      state.login.username = payload;
    },
  },
  actions: {
    GET_ALL_USERS(context, payload) {
      searchUsers(payload)
        .then(({ data }) => {
          context.commit('SET_ALL_USERS', data);
        })
        .catch(err => {
          console.log('get all users actions err ', err);
        });
    },
    SET_NAME({ commit }, username) {
      commit('SET_NAME', username);
    },
  },
  getters: {
    isLogin(state) {
      return state.login.isLogin;
    },
    getUserid(state) {
      return state.login.userid;
    },
    userInfo(state) {
      return state.login;
    },
    users(state) {
      return state.users;
    },
  },
};
