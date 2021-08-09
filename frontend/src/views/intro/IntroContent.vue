<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col-4 text">
          <div class="me-3 button-login">
            <router-link to="/login"> test) Login </router-link>
          </div>
          <div class="me-3 button-meetingroom">
            <router-link to="/meetingroom"> MeetingRoom </router-link>
          </div>
        </div>
        <div class="col-8 image">
          <img src="@/assets/img/group.jpg" width="100%" />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'IntroPage',
};
</script>
<style scoped>
.div {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr;
  gap: 0px 0px;
  grid-auto-flow: row;
  justify-items: stretch;
  align-items: stretch;
  grid-template-areas: 'hi hello';
}

.text {
  justify-self: stretch;
  align-self: center;
  grid-area: hi;
}

.image {
  align-self: stretch;
  grid-area: hello;
}

html,
body,
.div {
  height: 100%;
  margin: 0;
}

/* For presentation only, no need to copy the code below */

.div * {
  border: 1px solid red;
  position: relative;
}

.div *:after {
  content: attr(class);
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: grid;
  align-items: center;
  justify-content: center;
}
</style>
