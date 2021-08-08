<template>
  <div class="meeting-room">
    <!-- register -->
    <div v-if="participants === null">
      <h1>MeetingRoom</h1>
      <input type="text" v-model="name" />
      <button @click="register">register</button>
    </div>
    <!-- register -->
    <!-- groupcall -->
    <div v-if="participants !== null" class="meetingroom-container">
      <!-- left side bar -->
      <div id="left-sidebar" v-if="leftSideShow">
        <VideoUnitGroup
          class="left-side-bar"
          @toggleLeftSide="onToggleLeftSide"
        />
      </div>
      <!-- left side bar -->
      <!-- main Video -->
      <div
        id="main-video"
        :class="[
          { 'main-left-margin': leftSideShow },
          { 'main-right-margin': rightSideShow },
          'main-video',
        ]"
      >
        <MainVideoUnit
          :mainParticipant="mainParticipant"
          class="main-video-unit"
        />
        <MeetingRoomController
          class="controller"
          @toggleLeftSide="onToggleLeftSide"
          @toggleRightSide="onToggleRightSide"
        />
      </div>
      <!-- main Video -->
      <!-- right side bar -->
      <div id="right-sidebar" v-if="rightSideShow">
        <MeetingSideBar
          class="right-side-bar"
          @toggleRightSide="onToggleRightSide"
        />
      </div>
      <!-- right side bar -->
    </div>
    <!-- groupcall -->
  </div>
</template>

<script>
// import "./template.scss";
import VideoUnitGroup from './videoUnitGroup/VideoUnitGroup.vue';
import MainVideoUnit from './mainVideoUnit/MainVideoUnit.vue';
import MeetingSideBar from './meetingSideBar/MeetingSideBar.vue';
import MeetingRoomController from './meetingRoomController/MeetingRoomController.vue';

export default {
  name: 'MeetingRoom',
  components: {
    VideoUnitGroup,
    MainVideoUnit,
    MeetingSideBar,
    MeetingRoomController,
  },
  // : props
  props: {},
  // : data
  data() {
    return {
      name: null,
      leftSideShow: true,
      rightSideShow: true,
      /* mainVideoGrid: null, */
    };
  },
  // : watch
  watch: {
    serverMessage: function () {
      // console.log(this.serverMessage.id)
      switch (this.serverMessage.id) {
        case 'existingParticipants': {
          this.$store.dispatch(
            'meetingRoom/onExistingParticipants',
            this.serverMessage,
          );
          break;
        }
        case 'newParticipantArrived': {
          this.$store.dispatch(
            'meetingRoom/onNewParticipant',
            this.serverMessage,
          );
          break;
        }
        case 'participantLeft': {
          this.$store.dispatch(
            'meetingRoom/onParticipantLeft',
            this.serverMessage,
          );
          break;
        }
        case 'receiveVideoAnswer': {
          this.$store.dispatch(
            'meetingRoom/receiveVideoResponse',
            this.serverMessage,
          );
          break;
        }
        case 'iceCandidate': {
          const message = this.serverMessage;
          this.participants[message.name].rtcPeer.addIceCandidate(
            message.candidate,
            function (error) {
              if (error) {
                console.error('Error adding candidate: ' + error);
                return;
              }
            },
          );
          break;
        }
        default: {
          console.error('Unrecognized message' + this.serverMessage);
        }
      }
    },
  },
  // : computed
  computed: {
    serverMessage() {
      return this.$store.state.meetingRoom.serverMessage;
    },
    participants() {
      return this.$store.state.meetingRoom.participants;
    },
    ws() {
      return this.$store.state.meetingRoom.ws;
    },
    mainParticipant() {
      return this.participants[this.$store.state.meetingRoom.myName];
    },
  },
  // : lifecycle hook
  created() {
    const url = 'wss://' + location.host + '/groupcall';
    this.$store.dispatch('meetingRoom/wsInit', url);
  },
  mounted() {},
  // : methods
  methods: {
    register() {
      let name = this.name;
      let room = 'SaffyRoom';
      let message = {
        id: 'joinRoom',
        name: name,
        room: room,
      };
      this.$store.dispatch('meetingRoom/sendMessage', message);
      this.$store.dispatch('meetingRoom/setMyName', this.name);
    },
    onToggleLeftSide() {
      this.leftSideShow = !this.leftSideShow;
      /* this.arrangeGrid(); */
    },
    onToggleRightSide() {
      this.rightSideShow = !this.rightSideShow;
      console.log('toggleRightSide', this.rightSideShow);
      /* this.arrangeGrid(); */
    },
    /* arrangeGrid() {
      this.mainVideoGrid = 12;
      if (this.leftSideShow) {
        this.mainVideoGrid -= 2;
      }
      if (this.rightSideShow) {
        this.mainVideoGrid -= 3;
      }
      var colClass = 'col-' + String(this.mainVideoGrid);
      document.getElementById('main-video').className = colClass;
    }, */
  },
};
</script>

<style scoped>
.meetingroom-container {
  height: 100vh;
  width: 100vw;
  padding: 50px 35px;
  transition: 0.3s;
  background: #252f40;
}
.left-side-bar {
  background: none;
  width: 300px;
  top: 0;
  left: 0;
  position: fixed;
}
.main-video {
  background: none;
  width: auto;
  height: 50%;
}
.main-video-unit {
  width: auto;
  height: 100%;
  margin-top: 50px;
  margin-bottom: 50px;
}
.controller {
  position: absolute;
  bottom: 50px;
  left: 50%;
  transform: translate(-50%);
}
.right-side-bar {
  width: 400px;
  top: 0;
  right: 0;
  height: 100vh;
  position: fixed;
  transition: 0.3s;
}
.main-left-margin {
  margin-left: 320px;
}
.main-right-margin {
  margin-right: 420px;
}
</style>
