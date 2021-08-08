<template>
  <div>
    <!-- navigator -->
    <div class="d-flex justify-content-center mt-4">
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
    };
  },
  // : computed
  computed: {},
  // : lifecycle hook
  mounted() {},
  // : methods
  methods: {
    selectParticipantMenu: function () {
      this.participantShow = true;
    },
    selectPresentationMenu: function () {
      this.participantShow = false;
    },
  },
};
</script>

<style scoped>
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
  margin: 50px 25px;
}
</style>
