<template>
  <div>
    <!-- register -->
    <div v-if="participants===null">
      <h1>MeetingRoom</h1>
      <input type="text" v-model="name">
      <button @click="register">register</button>
    </div>
    <!-- register -->
    <!-- groupcall -->
    <div v-if="participants!==null">
      <div class="row">
        <div class="col-2">
          <VideoUnitGroup/>
        </div>
        <div class="col-7">
          <MainVideoUnit :mainParticipant="mainParticipant"/>
        </div>
        <div class="col-3">
          <MeetingSideBar/>
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

export default {
  name: 'MeetingRoom',
  components: {
    VideoUnitGroup,
    MainVideoUnit,
    MeetingSideBar,
  },
  // : props
  props: {
  },
  // : data
  data() {
    return {
      myName: null
    }
  },
  // : watch
  watch: {
    serverMessage: function () {
      console.log(this.serverMessage.id)
      switch (this.serverMessage.id) {
      case 'existingParticipants':
        this.$store.dispatch('meetingRoom/onExistingParticipants', this.serverMessage)
        break;
      case 'newParticipantArrived':
        this.$store.dispatch('meetingRoom/onNewParticipant', this.serverMessage)
        break;
      case 'participantLeft':
        this.$store.dispatch('meetingRoom/onParticipantLeft', this.serverMessage)
        break;
      case 'receiveVideoAnswer':
        this.$store.dispatch('meetingRoom/receiveVideoResponse', this.serverMessage)
        break;
      case 'iceCandidate':
        const message = this.serverMessage
        this.participants[message.name].rtcPeer.addIceCandidate(message.candidate, function (error) {
          if (error) {
            console.error("Error adding candidate: " + error)
            return
          }
        })
        break;
      default:
        console.error('Unrecognized message' + this.serverMessage)
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
  }
}
</script>