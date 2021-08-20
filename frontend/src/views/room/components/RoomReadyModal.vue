<template>
  <div
    class="modal fade"
    :id="'RoomReadyModal' + roomInfo.room_id"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalMessageTitle"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered text-lg" role="document">
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
          <video
            width="100%"
            :id="'local-video' + roomId"
            autoplay="true"
            poster="@/assets/img/logos/focus_camera3.jpg"
          ></video>
          <!-- local video element -->
          <i class="bi bi-mic-fill"></i>
          <button
            type="button"
            class="btn"
            :class="{
              'bg-gradient-dark': isMicOn,
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
              'bg-gradient-dark': isVideoOn,
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
import Vue from 'vue';
import VueAlertify from 'vue-alertify';
import { getRoomIsOnLive, setRoomOnLive } from '@/api/rooms.js';
import { mapGetters } from 'vuex';
Vue.use(VueAlertify);
export default {
  name: 'RoomReadyModal',
  props: { roomInfo: Object },
  components: {},
  data() {
    return {
      roomName: this.roomInfo.name,
      roomId: this.roomInfo.room_id,
      manager: this.roomInfo.manager_name + '-' + this.roomInfo.manager_id,
      userName: this.$store.state.users.login.username,
      userId: this.$store.state.users.login.userid,
      roomDescription: this.roomInfo.description,
      isMicOn: false,
      isVideoOn: false,
      srcObject: {},
      isManager: (this.roomInfo.manager_id =
        this.$store.state.users.login.userid),
    };
  },
  computed: { ...mapGetters(['room']) },
  mounted() {},
  methods: {
    micOnOff: function () {
      if (this.isMicOn) {
        this.isMicOn = false;
      } else {
        this.isMicOn = true;
      }
    },
    videoOnOff: function () {
      if (this.isVideoOn) {
        this.isVideoOn = false;
        this.stopVideoFromCamera();
      } else {
        this.isVideoOn = true;
        this.playVideoFromCamera();
      }
    },
    join: function () {
      if (this.isManager) {
        const roomData = {
          room_id: this.roomId,
          on_live: true,
        };
        setRoomOnLive(roomData)
          .then(({ status }) => {
            if (status != 200) {
              this.$alertify.error('방 상태변경에 실패했습니다.');
              return;
            }
            // this.$alertify.success('방 상태를 활동중으로 변경했습니다.');
            this.sendMsgToKurento();
          })
          .catch(() => {
            this.$alertify.error('setRoomOnLive error!');
          });
      } else {
        getRoomIsOnLive(this.roomId)
          .then(({ status }) => {
            if (status != 200) {
              this.$alertify.error('Manager 가 아직 방을 시작하지 않았습니다.');
              return;
            }
            this.sendMsgToKurento();
          })
          .catch(() => {
            this.$alertify.error('get room is on live error!');
          });
      }
    },

    sendMsgToKurento() {
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
        startWithMic: this.isMicOn,
        startWithVideo: this.isVideoOn,
      };
      this.$store.dispatch('meetingRoom/setMeetingInfo', meetingInfo);
      this.$store.dispatch('meetingRoom/sendMessage', message);
    },

    playVideoFromCamera: async function () {
      try {
        const constraints = { video: true, audio: false };
        const stream = await navigator.mediaDevices.getUserMedia(constraints);
        const videoElement = document.getElementById(
          'local-video' + this.roomId,
        );
        videoElement.srcObject = stream;
      } catch (error) {
        console.error('Error opening video camera.', error);
      }
    },
    stopVideoFromCamera: async function () {
      try {
        const videoElement = document.getElementById(
          'local-video' + this.roomId,
        );
        var stream = videoElement.srcObject;
        var tracks = stream.getTracks();

        for (var i = 0; i < tracks.length; i++) {
          var track = tracks[i];
          track.stop();
        }
        videoElement.srcObject = null;
      } catch (err) {
        console.error('Error stop video camera.', err);
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
