<template>
  <div>
    <div
      class="participant-item d-flex align-items-center justify-content-between"
    >
      <div class="d-flex align-items-center">
        <!-- <img src="@/assets/icons/user.svg" alt="" class="user-icon" /> -->
        <h4 class="username ms-5">{{ participantName }}</h4>
      </div>
      <!-- 사용자가 매니저인 경우만 표시 -->
      <img
        src="@/assets/icons/menu.svg"
        alt=""
        class="menu-icon"
        v-if="
          this.$store.state.meetingRoom.manager &&
          this.$store.state.meetingRoom.myName ===
            this.$store.state.meetingRoom.manager &&
          this.$store.state.meetingRoom.myName !== participant.name
        "
        @click="togglePopup"
      />
    </div>
    <!-- popup menu -->
    <transition name="popup-slide">
      <div
        v-if="showPopup"
        class="
          popup
          d-flex
          flex-column
          align-items-center
          justify-content-center
        "
      >
        <!-- popup menu items -->
        <div
          class="popup-item"
          @click="setPresenter"
          v-if="participant.name !== presenter"
        >
          <h4>발표자 지정</h4>
        </div>
        <div class="popup-item" @click="resetPresenter" v-else>
          <h4>발표자 지정 해제</h4>
        </div>
        <!-- popup menu items -->
      </div>
    </transition>
    <!-- popup menu -->
  </div>
</template>

<script>
// import "./template.scss";
import _ from 'lodash';

export default {
  name: 'MeetingParticipantsItem',
  components: {},
  // : props
  props: {
    participant: Object,
  },
  // : data
  data() {
    return {
      showPopup: false,
    };
  },
  // : computed
  computed: {
    participantName() {
      const nameOnServer = this.participant.name;
      return _.split(nameOnServer, '-')[0];
    },
    presenter() {
      return this.$store.state.meetingRoom.presenter;
    },
  },
  // : lifecycle hook
  mounted() {},
  // : methods
  methods: {
    togglePopup: function () {
      this.showPopup = !this.showPopup;
    },
    setPresenter: function () {
      const message = {
        id: 'setPresenter',
        presenter: this.participant.name,
      };
      this.$store.dispatch('meetingRoom/sendMessage', message);
    },
    resetPresenter: function () {
      const message = {
        id: 'setPresenter',
        presenter: this.$store.state.meetingRoom.manager,
      };
      this.$store.dispatch('meetingRoom/sendMessage', message);
    },
  },
};
</script>

<style scoped>
.participant-item {
  background: linear-gradient(90deg, #2c3153 0%, #15182a 100%);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  height: 50px;
  width: 350px;
  margin-top: 25px;
  border-radius: 25px;
}
.user-icon {
  margin-left: 15px;
  margin-right: 5px;
}
.username {
  color: white;
}
.menu-icon {
  cursor: pointer;
}
.popup {
  position: relative;
  width: 350px;
  height: 50px;
  margin: 25px 0 0 0;
}
.popup-item {
  background: linear-gradient(90deg, #a0b0d0 0%, #7587a6 100%);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  width: 250px;
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  border-radius: 25px;
}
.popup-item:hover {
  background: linear-gradient(90deg, #2c3153 0%, #15182a 100%);
}
.popup-slide-enter-active {
  animation: fadeIn 0.5s;
}
.popup-item h4 {
  color: white;
  font-weight: bold;
}
</style>
