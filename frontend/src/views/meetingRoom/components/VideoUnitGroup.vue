<template>
  <div class="d-flex flex-column justify-content-center align-items-center">
    <img
      src="@/assets/icons/up.svg"
      alt=""
      class="up-button"
      v-if="first > 0"
      @click="onPrevVideos"
    />
    <!-- 참가자가 한 명 뿐일 때 -->
    <div
      v-if="participants === null || participants.length === 0"
      class="video-container"
    >
      <img src="@/assets/icons/empty.svg" alt="" class="video-insert" />
    </div>
    <!-- 객체 v-for 시 index를 key로 주면 발표자 변경 시 비디오 렌더링 문제 발생, key를 name으로 변경 -->
    <VideoUnit
      class="video-container"
      v-for="(participant, index) in participants"
      :key="participant.name"
      :participant="participant"
      v-show="index >= first && index <= last"
    />
    <!--  -->
    <img
      src="@/assets/icons/down.svg"
      alt=""
      class="down-button"
      v-if="participants !== null && last < participants.length - 1"
      @click="onNextVideos"
    />
  </div>
</template>

<script>
import VideoUnit from './VideoUnit.vue';

export default {
  name: 'VideoUnitGroup',
  components: { VideoUnit },
  // : props
  props: {},
  // : data
  data() {
    return {
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
      if (this.allParticipants) {
        var participantList = [];
        Object.keys(this.allParticipants).forEach(key => {
          if (key !== this.$store.state.meetingRoom.presenter) {
            participantList.push(this.allParticipants[key]);
          }
        });
        return participantList;
      } else {
        return null;
      }
    },
  },
  // : lifecycle hook
  mounted() {},
  // : methods
  methods: {
    // 0번 비디오 밑으로는 내려가지 않음
    onPrevVideos: function () {
      this.first -= 1;
      this.last -= 1;
    },
    // 마지막 참가지 비디오 이상으로는 올라가지 않음
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
