export default {
  namespaced: true,
  state: () => ({
    test: 'test',
    login: {
      isLogin: false,
      useremail: '',
      username: '',
      userpwd: '',
    },
  }),
  mutations: {
    SET_LOGIN(state, payload) {
      state.login.isLogin = true;
      state.login.username = payload.name;
      state.login.useremail = payload.email;
      state.login.userpwd = payload.password;
    },
    SET_LOGOUT(state) {
      state.login.isLogin = false;
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
