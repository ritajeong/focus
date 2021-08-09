<template>
  <div class="meeting-room">
    <!-- register -->
    <div v-if="participants === null">
      <h1>MeetingRoom</h1>
      <input type="text" v-model="name">
      <button @click="register">register</button>
    </div>
    <!-- register -->
    <!-- groupcall -->
    <div v-if="participants !== null" class="main-container">
      <div class="row">
        <div class="col-2" id="left-sidebar" v-show="leftSideShow">
          <VideoUnitGroup class="section" @toggleLeftSide="onToggleLeftSide"/>
        </div>
        <div class="col-7" id="main-video">
          <div class="d-flex flex-column justify-content-center section">
            <MainVideoUnit :mainParticipant="mainParticipant"/>
            <MeetingRoomController @toggleLeftSide="onToggleLeftSide" @toggleRightSide="onToggleRightSide"/>
          </div>
        </div>
        <div class="col-3" id="right-sidebar" v-show="rightSideShow">
          <MeetingSideBar class="section" @toggleRightSide="onToggleRightSide"/>
        </div>
      </div>
    </div>
    <!-- groupcall -->
  </div>
</template>

<script>
// import "./template.scss";
import VideoUnitGroup from './videoUnitGroup/VideoUnitGroup.vue'
import MainVideoUnit from './mainVideoUnit/MainVideoUnit.vue'
import MeetingSideBar from './meetingSideBar/MeetingSideBar.vue'
import MeetingRoomController from './meetingRoomController/MeetingRoomController.vue'

export default {
  name: 'MeetingRoom',
  components: {
    VideoUnitGroup,
    MainVideoUnit,
    MeetingSideBar,
    MeetingRoomController,
  },
  // : props
  props: {
  },
  // : data
  data() {
    return {
      name: null,
      leftSideShow: true,
      rightSideShow: true,
      mainVideoGrid: null,
    }
  },
  // : watch
  watch: {
    serverMessage: function () {
      // console.log(this.serverMessage.id)
      switch (this.serverMessage.id) {
      case 'existingParticipants':{
        this.$store.dispatch('meetingRoom/onExistingParticipants', this.serverMessage)
        break;
      }
      case 'newParticipantArrived':{
        this.$store.dispatch('meetingRoom/onNewParticipant', this.serverMessage)
        break;
      }
      case 'participantLeft':{
        this.$store.dispatch('meetingRoom/onParticipantLeft', this.serverMessage)
        break;
      }
      case 'receiveVideoAnswer':{
        this.$store.dispatch('meetingRoom/receiveVideoResponse', this.serverMessage)
        break;
      }
      case 'iceCandidate':{
        const message = this.serverMessage
        this.participants[message.name].rtcPeer.addIceCandidate(message.candidate, function (error) {
          if (error) {
            console.error("Error adding candidate: " + error)
            return
          }
        })
        break;
      }
      default:{
        console.error('Unrecognized message' + this.serverMessage)
      }
      }
    },
  },
  // : computed
  computed: {
    serverMessage() {
      return this.$store.state.meetingRoom.serverMessage
    },
    participants() {
      return this.$store.state.meetingRoom.participants
    },
    ws() {
      return this.$store.state.meetingRoom.ws
    },
    mainParticipant() {
      return this.participants[this.$store.state.meetingRoom.myName]
    }
  },
  // : lifecycle hook
  created() {
    const url = 'wss://' + location.host + '/groupcall'
    this.$store.dispatch('meetingRoom/wsInit', url)
  },
  mounted() {
  },
  // : methods
  methods: {
    register() {
      let name = this.name
      let room = 'SaffyRoom'
      let message = {
        id: 'joinRoom',
        name: name,
        room: room,
      }
      this.$store.dispatch('meetingRoom/sendMessage', message)
      this.$store.dispatch('meetingRoom/setMyName', this.name)
    },
    onToggleLeftSide() {
      this.leftSideShow = !this.leftSideShow
      this.arrangeGrid()
    },
    onToggleRightSide() {
      this.rightSideShow = !this.rightSideShow
      console.log('toggleRightSide', this.rightSideShow)
      this.arrangeGrid()
    },
    arrangeGrid() {
      this.mainVideoGrid = 12
      if (this.leftSideShow) {
        this.mainVideoGrid -= 2
      }
      if (this.rightSideShow) {
        this.mainVideoGrid -= 3
      }
      var colClass = 'col-' + String(this.mainVideoGrid)
      document.getElementById('main-video').className = colClass
    }
  }
}
</script>

<style scoped>
  .main-container {
    height: 100%;
  }
  .section {
    height: 100%;
  }
</style>