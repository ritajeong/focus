<template>
  <div style="padding-bottom: 70px">
    <!--    -->
    <!-- presentation controller componenets -->
    <PresentationContentSelector v-if="contentShow" />
    <PresentationLocationSelector v-if="locationShow" />
    <PresentationEffectSelector v-if="effectShow" />
    <PresentationSlider v-if="sliderShow" />
    <!-- presentation controller componenets -->
    <!-- presentation controller navigator -->
    <div class="prsent-control-navigator">
      <div class="d-flex justify-content-center">
        <div>
          <button
            :class="[
              { 'navigator-button-active': contentShow },
              { 'navigator-button-inactive': !contentShow },
              'navigator-button',
              'navigator-button-start',
            ]"
            @click="selectContentMenu"
          >
            Content
          </button>
          <button
            :class="[
              { 'navigator-button-active': locationShow },
              { 'navigator-button-inactive': !locationShow },
              'navigator-button',
            ]"
            @click="selectLocationMenu"
          >
            Location
          </button>
        </div>
        <div>
          <button
            :class="[
              { 'navigator-button-active': effectShow },
              { 'navigator-button-inactive': !effectShow },
              'navigator-button',
            ]"
            @click="selectEffectMenu"
          >
            Effect
          </button>
          <button
            :class="[
              { 'navigator-button-active': sliderShow },
              { 'navigator-button-inactive': !sliderShow },
              'navigator-button',
              'navigator-button-end',
            ]"
            @click="selectSliderMenu"
          >
            Slider
          </button>
        </div>
      </div>
      <!-- access alert -->
      <transition name="fade">
        <div v-if="alertShow" class="access-alert">
          <h5 class="mb-0">발표자료를 선택하세요.</h5>
        </div>
      </transition>
    </div>
    <!-- presentation controller navigator -->
  </div>
</template>

<script>
import PresentationContentSelector from './PresentationContentSelector.vue';
import PresentationLocationSelector from './PresentationLocationSelector.vue';
import PresentationSlider from './PresentationSlider.vue';
import PresentationEffectSelector from './PresentationEffectSelector.vue';

export default {
  name: 'PresentationController',
  components: {
    PresentationContentSelector,
    PresentationLocationSelector,
    PresentationSlider,
    PresentationEffectSelector,
  },
  // : props
  props: {},
  // : data
  data() {
    return {
      contentShow: true,
      locationShow: false,
      effectShow: false,
      sliderShow: false,
      alertShow: false,
      /* 이 외 slide controller의 모든 데이터는 발표와 관련(다른 사람과 동일한 데이터를 유지해야함) 있으므로 state에서 관리  */
    };
  },
  // : computed
  computed: {},
  // : lifecycle hook
  mounted() {},
  // : methods
  methods: {
    selectContentMenu: function () {
      this.contentShow = true;
      this.locationShow = false;
      this.effectShow = false;
      this.sliderShow = false;
    },
    selectLocationMenu: function () {
      if (this.$store.state.meetingRoom.imageSrcs === null) {
        this.activateAlert();
      } else {
        this.contentShow = false;
        this.locationShow = true;
        this.effectShow = false;
        this.sliderShow = false;
      }
    },
    selectEffectMenu: function () {
      if (this.$store.state.meetingRoom.imageSrcs === null) {
        this.activateAlert();
      } else {
        this.contentShow = false;
        this.locationShow = false;
        this.effectShow = true;
        this.sliderShow = false;
      }
    },
    selectSliderMenu: function () {
      if (this.$store.state.meetingRoom.imageSrcs === null) {
        this.activateAlert();
      } else {
        this.contentShow = false;
        this.locationShow = false;
        this.effectShow = false;
        this.sliderShow = true;
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
.prsent-control-navigator {
  background: #e9ecef;
  position: fixed;
  bottom: 0;
  right: 0;
  height: 85px;
  width: 400px;
  display: flex;
  align-items: center;
  justify-content: center;
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
.navigator-button {
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border: none;
  width: 87.5px;
  height: 35px;
  color: white;
  font-weight: bold;
}
.navigator-button-start {
  border-radius: 30px 0px 0px 30px;
}
.navigator-button-end {
  border-radius: 0px 30px 30px 0px;
}
.navigator-button-active {
  background: linear-gradient(90deg, #2c3153 0%, #15182a 100%);
}
.navigator-button-inactive {
  background: linear-gradient(90deg, #a0b0d0 0%, #7587a6 100%);
}
</style>
