<template>
  <div>
    <header id="header" class="fixed-top d-flex align-items-center">
      <div class="container d-flex align-items-center justify-content-between">
        <div class="logo">
          <h1><router-link to="/"> FOCUS </router-link></h1>
          <!-- Uncomment below if you prefer to use an image logo -->
          <!-- <a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
        </div>

        <nav id="navbar" class="navbar">
          <ul>
            <li class="nav-link scrollto">
              <router-link to="/dashboard">DashBoard</router-link>
            </li>
            <li class="nav-link scrollto">
              <router-link to="/meetingroom">MeetingRoom</router-link>
            </li>
            <li class="nav-link scrollto">
              <router-link to="/setting">Seeting</router-link>
            </li>
            <li class="dropdown">
              <router-link to="/mypage"><span>MyPage</span></router-link>
              <i class="bi bi-chevron-down"></i>
              <ul>
                <li>
                  <router-link to="/mypage/presentation"
                    >MyPresentation</router-link
                  >
                </li>
                <li><router-link to="/mypage/note">MyNote</router-link></li>
              </ul>
            </li>
          </ul>
          <i class="bi bi-list mobile-nav-toggle"></i>
        </nav>
        <!-- .navbar -->
      </div>
    </header>
    <!-- End Header -->
  </div>
</template>

<script>
// import "bootstrap/dist/css/bootstrap.min.css";
// import "bootstrap/dist/js/bootstrap.min.js";

export default {
  name: 'AppNav',
};
</script>
