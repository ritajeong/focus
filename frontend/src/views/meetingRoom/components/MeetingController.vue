<template>
  <div class="d-flex controller" @keyup.right="progressNext">
    <button class="controller-button mx-3" @click="toggleVideo">
      <div v-if="myVideoEnabled">비디오 중지</div>
      <div v-else>비디오 시작</div>
    </button>
    <button class="controller-button mx-3" @click="toggleAudio">
      <div v-if="myAudioEnabled">음소거</div>
      <div v-else>음소거 해제</div>
    </button>
    <!-- 발표중지: 매니저이고, 다른 사람이 발표자일 때 보임 -->
    <button
      class="controller-button mx-3"
      v-if="myName === manager && myName !== presenter"
      @click="stopPresentation"
    >
      발표 중지
    </button>
    <button class="controller-button mx-3" @click="leaveRoom">나가기</button>
  </div>
</template>

<script>
// import "./template.scss";

export default {
  name: 'MeetingController',
  components: {},
  // : props
  props: {},
  // : data
  data() {
    return {
      myVideoEnabled: null,
      myAudioEnabled: null,
    };
  },
  // : computed
  computed: {
    myParticipantObject() {
      return this.$store.state.meetingRoom.participants[
        this.$store.state.meetingRoom.myName
      ];
    },
    myName() {
      return this.$store.state.meetingRoom.myName;
    },
    manager() {
      return this.$store.state.meetingRoom.manager;
    },
    presenter() {
      return this.$store.state.meetingRoom.presenter;
    },
    currentPage() {
      return this.$store.state.meetingRoom.currentPage;
    },
    contentLength() {
      return this.$store.state.meetingRoom.imageSrcs.length;
    },
    messageData() {
      const data = {
        id: 'changePresentation',
        location: this.$store.state.meetingRoom.location,
        size: this.$store.state.meetingRoom.size,
        transition: this.$store.state.meetingRoom.transition,
      };
      return data;
    },
  },
  // : lifecycle hook
  mounted() {
    this.myVideoEnabled = this.$store.state.meetingRoom.startWithVideo;
    this.myAudioEnabled = this.$store.state.meetingRoom.startWithMic;
  },
  // : methods
  methods: {
    toggleVideo: function () {
      this.myParticipantObject.rtcPeer.videoEnabled = !this.myVideoEnabled;
      this.myVideoEnabled = !this.myVideoEnabled;
    },
    toggleAudio: function () {
      this.myParticipantObject.rtcPeer.audioEnabled = !this.myAudioEnabled;
      this.myAudioEnabled = !this.myAudioEnabled;
    },
    stopPresentation: function () {
      const message = {
        id: 'setPresenter',
        presenter: this.manager,
      };
      this.$store.dispatch('meetingRoom/sendMessage', message);
    },
    leaveRoom: function () {
      const message = {
        id: 'leaveRoom',
      };
      this.$store.dispatch('meetingRoom/sendMessage', message);
      this.$store.dispatch('meetingRoom/leaveRoom');
    },
    progressNext: function () {
      const message = {
        ...this.messageData,
        currentPage: this.currentPage + 1,
      };
      this.$store.dispatch('meetingRoom/sendMessage', message);
    },
  },
};
</script>

<style scoped>
.controller-button {
  background: linear-gradient(90deg, #f10488 0%, #a51bb2 100%);
  box-shadow: 0px 4px 4px black;
  border: none;
  width: 150px;
  height: 40px;
  border-radius: 30px;
  color: white;
  font-weight: bold;
}
.controller-button-disabled {
  background: linear-gradient(90deg, #a0b0d0 0%, #7587a6 100%);
  box-shadow: 0px 4px 4px black;
  border: none;
  width: 150px;
  height: 40px;
  border-radius: 30px;
  color: white;
  font-weight: bold;
}
</style>
