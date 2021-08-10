export default {
  namespaced: true,
  state: () => ({
    login: {
      isLogin: false,
      useremail: '',
      username: '',
    },
  }),
  mutations: {
    SET_LOGIN(state, payload) {
      state.login.isLogin = payload.isLogin;
      state.login.username = payload.name;
      state.login.useremail = payload.email;
    },
    SET_LOGOUT(state) {
      state.login.isLogin = '';
      state.login.username = '';
      state.login.useremail = '';
    },
  },
  actions: {},
  getters: {
    isLogin(state) {
      return state.login.isLogin !== '';
    },
  },
};
