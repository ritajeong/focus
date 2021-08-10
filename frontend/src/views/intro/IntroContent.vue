<template>
  <!-- -------- START HEADER 1 w/ text and image on right ------- -->
  <header>
    <div class="page-header min-vh-100">
      <div class="oblique position-absolute top-0 h-100 d-md-block d-none">
        <div
          class="
            oblique-image
            bg-cover
            position-absolute
            fixed-top
            ms-auto
            h-100
            z-index-0
            ms-n6
          "
          style="
            background-image: url(../../assets/img/curved-images/curved-11.jpg);
          "
        ></div>
      </div>
      <div class="container">
        <div class="row">
          <div
            class="col-lg-6 col-md-7 d-flex justify-content-center flex-column"
          >
            <h1 class="text-gradient text-dark">Your Work With</h1>
            <h1 class="text-gradient text-dark mb-4">Soft Design System</h1>
            <p class="lead pe-5 me-5">
              The time is now for it be okay to be great. People in this world
              shun people for being nice.
            </p>
            <div class="buttons">
              <router-link to="/login" class="btn bg-gradient-dark mt-4">
                Login
              </router-link>

              <router-link
                to="/meetingroomenter"
                class="btn btn-outline-dark text-dark shadow-none mt-4"
              >
                MeetingRoom
              </router-link>

              <router-link
                to="/dashboard"
                class="btn btn-outline-dark text-dark shadow-none mt-4"
              >
                Dashboard
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </header>
  <!-- -------- END HEADER 1 w/ text and image on right ------- -->
</template>

<script>
export default {
  name: 'IntroPage',
};
</script>
