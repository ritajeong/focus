<template>
  <div style="padding-bottom: 70px">
    <!--    -->
    <!-- presentation controller componenets -->
    <PresentationContentSelector v-if="contentShow" />
    <PresentationLocationSelector v-if="locationShow" />
    <PresentationSlider v-if="sliderShow" />
    <!-- presentation controller componenets -->
    <!-- presentation controller navigator -->
    <div class="prsent-control-navigator">
      <div class="d-flex justify-content-center">
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
          Loction
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
    <!-- presentation controller navigator -->
  </div>
</template>

<script>
import PresentationContentSelector from './PresentationContentSelector.vue';
import PresentationLocationSelector from './PresentationLocationSelector.vue';
import PresentationSlider from './PresentationSlider.vue';

export default {
  name: 'PresentationController',
  components: {
    PresentationContentSelector,
    PresentationLocationSelector,
    PresentationSlider,
  },
  // : props
  props: {},
  // : data
  data() {
    return {
      contentShow: true,
      locationShow: false,
      sliderShow: false,
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
      this.sliderShow = false;
    },
    selectLocationMenu: function () {
      this.contentShow = false;
      this.locationShow = true;
      this.sliderShow = false;
    },
    selectSliderMenu: function () {
      this.contentShow = false;
      this.locationShow = false;
      this.sliderShow = true;
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
.navigator-button {
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border: none;
  width: 116px;
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
