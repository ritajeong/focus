export default {
  namespaced: true,
  state: () => ({
    login: {
      isLogin: false,
      userName: '',
      userPwd: '',
      userEmail: '',
    },
  }),
  mutations: {
    SET_LOGIN(state, payload) {
      state.login.isLogin = payload.isLogin;
      state.login.userName = payload.name;
      state.login.userEmail = payload.email;
    },
  },
  actions: {
    wsInit(context, url) {
      context.commit('WS_INIT', url);
      context.state.ws.onmessage = function (message) {
        let parsedMessage = JSON.parse(message.data);
        // console.info('Received message: ' + message.data)
        context.commit('WS_ONMESSAGE', parsedMessage);
      };
    },
  },
  getters: {
    isLogin: function (state) {
      return state.login.isLogin;
    },
  },
};
