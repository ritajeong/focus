<template>
  <!-- Navbar Dark -->
  <nav
    class="navbar navbar-expand-lg navbar-dark bg-gradient-dark z-index-3 py-0"
  >
    <div class="container">
      <router-link
        to="/dashboard"
        class="navbar-brand text-white"
        rel="tooltip"
        title="Designed and Coded by Creative Tim"
        data-placement="bottom"
      >
        <h5 class="text-white">FOCUS</h5>
      </router-link>
      <!-- <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navigation"
        aria-controls="navigation"
        aria-expanded="false"
        aria-label="Toggle navigation"
      > 
        <span class="navbar-toggler-icon"></span>
      </button> -->

      <div
        class="collapse navbar-collapse w-100 pt-3 pb-2 py-lg-0"
        id="navigation"
      >
        <ul class="navbar-nav navbar-nav-hover mx-auto">
          <li class="nav-item mx-2">
            <router-link
              to="/dashboard"
              class="
                nav-link
                ps-2
                d-flex
                justify-content-between
                cursor-pointer
                align-items-center
              "
              ><h5 class="text-white">Dashboard</h5></router-link
            >
          </li>
          <!-- 
          <li class="nav-item mx-2">
            <router-link
              to="/meetingroom"
              class="
                nav-link
                ps-2
                d-flex
                justify-content-between
                cursor-pointer
                align-items-center
              "
              ><h6 class="text-white">Meeting Room</h6></router-link
            >
          </li> -->
          <!-- <li class="nav-item mx-2">
            <router-link
              to="/setting"
              class="
                nav-link
                ps-2
                d-flex
                justify-content-between
                cursor-pointer
                align-items-center
              "
              >Setting</router-link
            >
          </li> -->
          <li class="nav-item mx-2">
            <router-link
              to="/mypage"
              class="
                nav-link
                ps-2
                d-flex
                justify-content-between
                cursor-pointer
                align-items-center
              "
              ><h5 class="text-white">My Page</h5></router-link
            >
          </li>
        </ul>
        <router-link
          to="/"
          class="
            nav-link
            ps-2
            d-flex
            justify-content-between
            cursor-pointer
            align-items-center
            text-white
          "
          @click.native="userLogout()"
          ><h5 class="text-white">Logout</h5></router-link
        >
      </div>
    </div>
  </nav>
  <!-- End Navbar -->
</template>

<script>
import Vue from 'vue';
import VueAlertify from 'vue-alertify';
Vue.use(VueAlertify);

export default {
  name: 'AppNav',
  computed: {},

  methods: {
    userLogout() {
      this.$alertify.success('안녕히 가세요');
      this.$store.commit('users/SET_LOGOUT');
      console.log('logout');
      // this.$router.push('/', () => {});
    },
  },
};
</script>
