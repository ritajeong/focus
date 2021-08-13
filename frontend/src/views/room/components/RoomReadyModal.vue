<template>
  <div
    class="modal fade"
    id="exampleModalMessage"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalMessageTitle"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">{{ roomName }}</h5>
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
          <video width="100%" id="video" autoplay="true"></video>
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
  props: {},
  data() {
    return {
      roomName: 'testest',
      userName: 'mann',
      userId: '1',
      roomDescription: '방 설명',
      isMicOn: true,
      isVideoOn: true,
    };
  },
  computed: {},
  mounted() {
    const url = 'wss://' + location.host + '/groupcall';
    this.$store.dispatch('meetingRoom/wsInit', url);
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
      const roomId = '';
      const myNameId = this.userName + '-' + this.userId;
      const roomNameId = this.roomName + '-' + roomId;
      const message = {
        id: 'joinRoom',
        name: myNameId,
        room: roomNameId,
      };
      this.$store.dispatch('meetingRoom/sendMessage', message);
      this.$store.dispatch('meetingRoom/setMyName', myNameId);
    },
  },
};
</script>
<style scoped>
.btn {
  margin: 4px;
}
</style>
