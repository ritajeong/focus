<template>
  <div>
    <!-- navigator -->
    <div class="d-flex justify-content-center navigator">
      <button
        :class="[
          { 'navigator-button-active': participantShow },
          { 'navigator-button-inactive': !participantShow },
          'navigator-participant-button',
        ]"
        @click="selectParticipantMenu"
      >
        Participants
      </button>
      <button
        :class="[
          { 'navigator-button-active': !participantShow },
          { 'navigator-button-inactive': participantShow },
          'navigator-presentation-button',
        ]"
        @click="selectPresentationMenu"
      >
        Presentation
      </button>
    </div>
    <!-- navigator -->
    <!-- SideBar Items -->
    <MeetingParticipants v-if="participantShow" class="meeting-participants" />
    <PresentationController
      v-if="!participantShow"
      class="presentation-controller"
    />
    <!-- SideBar Items -->
    <!-- access alert -->
    <transition name="fade">
      <div v-if="alertShow" class="access-alert">
        <h5 class="mb-0">발표자만 사용할 수 있습니다.</h5>
      </div>
    </transition>
  </div>
</template>

<script>
import MeetingParticipants from './MeetingParticipants.vue';
import PresentationController from './PresentationController.vue';

export default {
  name: 'MeetingSideBar',
  components: { MeetingParticipants, PresentationController },
  // : props
  props: {},
  // : data
  data() {
    return {
      participantShow: true,
      alertShow: false,
    };
  },
  // : computed
  computed: {
    myName() {
      return this.$store.state.meetingRoom.myName;
    },
    presenter() {
      return this.$store.state.meetingRoom.presenter;
    },
  },
  // : lifecycle hook
  mounted() {},
  // : methods
  methods: {
    selectParticipantMenu: function () {
      this.participantShow = true;
    },
    selectPresentationMenu: function () {
      if (this.myName === this.presenter) {
        this.participantShow = false;
      } else {
        this.activateAlert();
      }
    },
    activateAlert: function () {
      this.alertShow = true;
      setTimeout(this.inactivateAlert, 2000);
    },
    inactivateAlert: function () {
      this.alertShow = false;
    },
  },
};
</script>

<style scoped>
.navigator {
  margin-top: 25px;
}
.navigator-participant-button {
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border: none;
  width: 175px;
  height: 35px;
  border-radius: 30px 0px 0px 30px;
  color: white;
  font-weight: bold;
}
.navigator-presentation-button {
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border: none;
  width: 175px;
  height: 35px;
  border-radius: 0px 30px 30px 0px;
  color: white;
  font-weight: bold;
}
.navigator-button-active {
  background: linear-gradient(90deg, #2c3153 0%, #15182a 100%);
}
.navigator-button-inactive {
  background: linear-gradient(90deg, #a0b0d0 0%, #7587a6 100%);
}
.meeting-participants {
  margin: 25px 25px;
}
.presentation-controller {
  margin: 30px 25px 0;
}
.access-alert {
  position: absolute;
  left: 50%;
  top: 7%;
  width: 75%;
  border-radius: 10px;
  background: rgba(255, 255, 255, 0.774);
  color: black;
  display: flex;
  justify-content: center;
  align-items: center;
  transform: translate(-50%);
  transition: 0.5s;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>
