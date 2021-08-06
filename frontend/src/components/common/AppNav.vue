<template>
  <!-- Navbar Dark -->
  <nav
    class="navbar navbar-expand-lg navbar-dark bg-gradient-dark z-index-3 py-3"
  >
    <div class="container">
      <router-link
        to="/"
        class="navbar-brand text-white"
        rel="tooltip"
        title="Designed and Coded by Creative Tim"
        data-placement="bottom"
        target="_blank"
      >
        FOCUS
      </router-link>
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
              >DashBoard</router-link
            >
          </li>

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
              >MeetingRoom</router-link
            >
          </li>
          <li class="nav-item mx-2">
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
              >Seeting</router-link
            >
          </li>
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
              >Mypage</router-link
            >
          </li>
        </ul>

        <ul class="navbar-nav d-lg-block d-none">
          <li class="nav-item">
            <a
              href="https://www.creative-tim.com/product/soft-ui-design-system-pro#pricingCard"
              class="btn btn-sm bg-gradient-primary btn-round mb-0 me-1"
              role="button"
              >Button</a
            >
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- End Navbar -->
</template>

<script>
export default {
  name: 'AppNav',
};
</script>
