<template>
  <div class="meetingroom-container">
    <transition name="left-slide">
      <VideoUnitGroup class="left-side-bar" v-if="leftSideShow" />
    </transition>
    <div
      :class="[
        { 'main-left-margin': leftSideShow },
        { 'main-right-margin': rightSideShow },
        'main',
        'd-flex',
        'flex-column',
        'align-items-center',
        'justify-content-center',
      ]"
    >
      <!-- left side bar control buttons -->
      <transition name="button-show"
        ><img
          src="@/assets/icons/left.svg"
          alt=""
          :class="[{ 'button-left-margin': leftSideShow }, 'left-side-toggler']"
          v-if="leftSideShow"
          @click="toggleLeftSide"
      /></transition>
      <transition name="button-show"
        ><img
          src="@/assets/icons/right.svg"
          alt=""
          class="left-side-toggler"
          v-if="!leftSideShow"
          @click="toggleLeftSide"
      /></transition>
      <!-- left side bar control buttons -->
      <h1 class="room-title">Room Title</h1>
      <MainVideoUnit class="mt-4" />
      <MeetingController class="mt-4" />
      <!-- right side bar control buttons -->
      <transition name="button-show"
        ><img
          src="@/assets/icons/right.svg"
          alt=""
          :class="[
            { 'button-right-margin': rightSideShow },
            'right-side-toggler',
          ]"
          v-if="rightSideShow"
          @click="toggleRightSide"
      /></transition>
      <transition name="button-show"
        ><img
          src="@/assets/icons/left.svg"
          alt=""
          class="right-side-toggler"
          v-if="!rightSideShow"
          @click="toggleRightSide"
      /></transition>
      <!-- right side bar control buttons -->
    </div>
    <transition name="right-slide">
      <MeetingSideBar class="right-side-bar" v-if="rightSideShow" />
    </transition>
  </div>
</template>

<script>
import VideoUnitGroup from './components/VideoUnitGroup.vue';
import MainVideoUnit from './components/MainVideoUnit.vue';
import MeetingController from './components/MeetingController.vue';
import MeetingSideBar from './components/MeetingSideBar.vue';

export default {
  name: 'MeetingRoom',
  components: {
    VideoUnitGroup,
    MainVideoUnit,
    MeetingController,
    MeetingSideBar,
  },
  // : props
  props: {},
  // : data
  data() {
    return {
      leftSideShow: true,
      rightSideShow: true,
    };
  },
  // : computed
  computed: {},
  // : lifecycle hook
  mounted() {},
  // : methods
  methods: {
    toggleLeftSide: function () {
      this.leftSideShow = !this.leftSideShow;
    },
    toggleRightSide: function () {
      this.rightSideShow = !this.rightSideShow;
    },
  },
};
</script>

<style scoped>
.meetingroom-container {
  height: 100vh;
  width: 100vw;
  padding: 20px 20px;
  background: linear-gradient(90deg, #2c3153 0%, #15182a 100%);
}
.left-side-bar {
  background: none;
  height: 100vh;
  width: 250px;
  top: 0;
  left: 20px;
  position: fixed;
}
.main {
  background: none;
  width: auto;
  height: 100%;
}
.right-side-bar {
  width: 400px;
  top: 0;
  right: 0;
  height: 100vh;
  background: #e9ecef;
  position: fixed;
  overflow: auto;
}
.main-left-margin {
  margin-left: 255px;
  transition: 0.3s;
}
.main-right-margin {
  margin-right: 390px;
  transition: 0.3s;
}
.button-left-margin {
  margin-left: 255px;
}
.button-right-margin {
  margin-right: 390px;
}
.left-side-toggler {
  position: absolute;
  left: 0%;
  top: 50%;
  cursor: pointer;
  transform: translate(100%, -50%);
}
.right-side-toggler {
  position: absolute;
  right: 0%;
  top: 50%;
  cursor: pointer;
  transform: translate(-100%, -50%);
}
.room-title {
  color: white;
}
.left-slide-enter-active {
  animation: slideInLeft 0.3s;
}
.left-slide-leave-active {
  animation: slideOutLeft 0.3s;
}
.right-slide-enter-active {
  animation: slideInRight 0.3s;
}
.right-slide-leave-active {
  animation: slideOutRight 0.3s;
}
.button-show-enter-active {
  animation-delay: 0.3s;
  visibility: hidden;
}
</style>
