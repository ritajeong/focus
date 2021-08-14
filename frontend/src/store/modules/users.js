import { searchUsers } from '@/api/users.js';
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
      console.log('login: ', payload);
      state.login.isLogin = true;
      state.login.userid = payload.id;
      state.login.username = payload.name;
      state.login.useremail = payload.email;
      //state.login.userpwd = payload.password;
    },
    SET_LOGOUT(state) {
      state.login.isLogin = false;
      state.login.userid = '';
      state.login.username = '';
      state.login.useremail = '';
      //state.login.userpwd = '';
    },
    SET_ALL_USERS(state, payload) {
      state.users = payload;
    },
  },
  actions: {
    GET_ALL_USERS(context, payload) {
      console.log('[actions] get all users');
      searchUsers(payload)
        .then(({ data }) => {
          context.commit('SET_ALL_USERS', data);
        })
        .catch(err => {
          console.log('get all users actions err ', err);
        });
    },
  },
  getters: {
    isLogin(state) {
      return state.login.isLogin;
    },
    userInfo(state) {
      return state.login;
    },
    users(state) {
      return state.users;
    },
  },
};
