<template>
  <div
    class="modal fade"
    :id="'RoomReadyModal' + roomInfo.room_id"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalMessageTitle"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" :id="'RoomReadyModal' + roomInfo.room_id">
            {{ roomName }}
          </h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          >
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">
          <form>
            <div class="form-group">
              <label for="message-text" class="col-form-label"
                >Room Description:
                <p>{{ roomDescription }}</p></label
              >
            </div>
          </form>
          <!-- local video element -->
          <video width="100%" id="local-video" autoplay="true"></video>
          <!-- local video element -->
          <i class="bi bi-mic-fill"></i>
          <button
            type="button"
            class="btn"
            :class="{
              'bg-gradient-warning': isMicOn,
              'bg-gradient-secondary': !isMicOn,
            }"
            @click="micOnOff"
          >
            <span
              class="fas"
              :class="{
                'fa-microphone': isMicOn,
                'fa-microphone-slash': !isMicOn,
              }"
            ></span>
          </button>
          <button
            type="button"
            class="btn"
            :class="{
              'bg-gradient-warning': isVideoOn,
              'bg-gradient-secondary': !isVideoOn,
            }"
            @click="videoOnOff"
          >
            <span
              class="fas fa-video"
              :class="{
                'fa-video': isVideoOn,
                'fa-video-slash': !isVideoOn,
              }"
            ></span>
          </button>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn bg-gradient-secondary"
            data-bs-dismiss="modal"
          >
            Close
          </button>
          <button
            type="button"
            class="btn bg-gradient-primary"
            @click="join"
            data-bs-dismiss="modal"
          >
            Join
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RoomReadyModal',
  components: {},
  props: { roomInfo: Object },
  data() {
    return {
      roomName: this.roomInfo.name,
      roomId: this.roomInfo.room_id,
      manager: this.roomInfo.manager_name + '-' + this.roomInfo.manager_id,
      userName: this.$store.state.users.login.username,
      userId: this.$store.state.users.login.userid,
      roomDescription: this.roomInfo.description,
      isMicOn: true,
      isVideoOn: true,
      srcObject: {},
    };
  },
  computed: {},
  mounted() {
    const url = 'wss://' + location.host + '/groupcall';
    this.$store.dispatch('meetingRoom/wsInit', url);

    /* if (navigator.mediaDevices.getUserMedia) {
      navigator.mediaDevices
        .getUserMedia({ video: true })
        .then(stream => {
          console.log('stream: ', stream);
          this.srcObject = stream;
        })
        .catch(function (err) {
          console.log('Something went wrong!', err);
        });
    } */
    this.playVideoFromCamera();
  },
  methods: {
    micOnOff: function () {
      if (this.isMicOn) {
        this.isMicOn = false;
      } else {
        this.isMicOn = true;
      }
      console.log('mic state: ', this.isMicOn);
    },
    videoOnOff: function () {
      if (this.isVideoOn) {
        this.isVideoOn = false;
      } else {
        this.isVideoOn = true;
      }
      console.log('video state: ', this.isVideoOn);
    },
    join: function () {
      const myNameId = this.userName + '-' + this.userId;
      const roomNameId = this.roomName + '-' + this.roomId;
      const message = {
        id: 'joinRoom',
        name: myNameId,
        room: roomNameId,
      };
      const meetingInfo = {
        myName: myNameId,
        roomName: roomNameId,
        manager: this.manager,
      };
      this.$store.dispatch('meetingRoom/sendMessage', message);
      this.$store.dispatch('meetingRoom/setMeetingInfo', meetingInfo);
    },
    playVideoFromCamera: async function () {
      try {
        const constraints = { video: true, audio: true };
        const stream = await navigator.mediaDevices.getUserMedia(constraints);
        const videoElement = document.getElementById('local-video');
        videoElement.srcObject = stream;
      } catch (error) {
        console.error('Error opening video camera.', error);
      }
    },
  },
};
</script>
<style scoped>
.btn {
  margin: 4px;
}
</style>
