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
            ms-n9
          "
          style="background-image: url(../../assets/img/gradient/Sky.jpg)"
        ></div>
      </div>
      <div
        class="
          col-xl-6 col-lg-5 col-md-5 col-sm-5
          position-absolute
          text-center
          end-0
          d-md-block
          align-middle
        "
        style="top: 30%"
      >
        <img src="../../assets/intro9.gif" style="width: 70%" />
      </div>
      <div class="container">
        <div class="row">
          <div
            class="
              col-xl-6 col-lg-8 col-md-7
              d-flex
              justify-content-center
              flex-column
            "
          >
            <h1 class="text-gradient text-focus mb-4 text-center">
              Focus on your presentation
            </h1>
            <h3 class="text-gradient text-focus mb-4 text-center">
              change the paradigm of video presentation
            </h3>
            <h4 class="text-gradient text-focus lead text-center mb-4">
              당신의 발표자료를 화상화면과 함께 띄워보세요
            </h4>
            <div class="buttons text-center">
              <router-link
                to="/login"
                class="btn bg-gradient-dark mt-4 w-20 text-lg"
              >
                Start
              </router-link>
              <!--ㅎㅇ isLogin===true?none:show, 테스트 후 meetingroom, dashboard 버튼 삭제-->

              <!-- <router-link
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
              </router-link> -->
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
<style scoped>
.text-gradient {
  background-clip: text;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  position: relative;
  z-index: 1;
}
.text-gradient.text-focus {
  background-image: linear-gradient(310deg, #141727, #3f92be);
}
</style>
