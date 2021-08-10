<template>
  <div class="d-flex flex-column justify-content-center align-items-center">
    <img
      src="@/assets/icons/up.svg"
      alt=""
      class="up-button"
      @click="onPrevVideos"
    />
    <!-- VideoUnitGroup 사람 많아질 때 다음으로 넘기는 것(index 이용) 코딩해야함!! -->
    <VideoUnit
      class="video-container"
      v-for="participant in participants"
      :key="participant.name"
      :participant="participant"
    />
    <!--  -->
    <img
      src="@/assets/icons/down.svg"
      alt=""
      class="down-button"
      @click="onNextVideos"
    />
  </div>
</template>

<script>
import VideoUnit from './VideoUnit.vue';
import Dummy from './Dummy.js';

export default {
  name: 'VideoUnitGroup',
  components: { VideoUnit },
  // : props
  props: {},
  // : data
  data() {
    return {
      /* 임시 데이터: videoUrls */
      videoUrls: Dummy.getVideoUrls(),
      first: 0,
      last: 3,
    };
  },
  // : computed
  computed: {
    allParticipants() {
      return this.$store.state.meetingRoom.participants;
    },
    participants() {
      var participantList = [];
      Object.keys(this.allParticipants).forEach(key => {
        if (key !== this.$store.state.meetingRoom.presenter) {
          participantList.push(this.allParticipants[key]);
        }
      });
      return participantList;
    },
  },
  // : lifecycle hook
  mounted() {},
  // : methods
  methods: {
    onPrevVideos: function () {
      this.first -= 1;
      this.last -= 1;
    },
    onNextVideos: function () {
      this.first += 1;
      this.last += 1;
    },
  },
};
</script>

<style scoped>
.video-container {
  height: 20%;
  width: auto;
  padding: 5px 5px;
}
.up-button {
  position: absolute;
  top: 50px;
  left: 50%;
  transform: translate(-50%);
  cursor: pointer;
}
.down-button {
  position: absolute;
  left: 50%;
  bottom: 50px;
  transform: translate(-50%);
  cursor: pointer;
}
.upslide-enter-active {
  animation: slideInUp 0.3s;
}
.upslide-leave-active {
  animation: slideOutUp 0.3s;
}
</style>
