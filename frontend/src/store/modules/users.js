export default {
  namespaced: true,
  state: () => ({
    test: 'test',
    login: {
      isLogin: false,
      userid: '',
      useremail: '',
      username: '',
      userpwd: '',
    },
  }),
  mutations: {
    SET_LOGIN(state, payload) {
      state.login.isLogin = true;
      state.login.userid = payload.id;
      state.login.username = payload.name;
      state.login.useremail = payload.email;
      state.login.userpwd = payload.password;
    },
    SET_LOGOUT(state) {
      state.login.isLogin = false;
      state.login.userid = '';
      state.login.username = '';
      state.login.useremail = '';
      state.login.userpwd = '';
    },
  },
  actions: {},
  getters: {
    isLogin(state) {
      return state.login.isLogin;
    },
    userInfo(state) {
      return state.login;
    },
  },
};
