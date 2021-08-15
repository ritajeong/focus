<template>
  <div>
    <!-- prev -->
    <PresentationSlideItem v-if="prev >= 0" :slideUrl="slideUrls[prev].url" />
    <img
      v-else
      src="@/assets/presentationTemplates/first-slide.png"
      alt=""
      class="template-insert"
    />
    <!-- now -->
    <div style="position: relative">
      <PresentationSlideItem :slideUrl="slideUrls[now].url" />
      <div class="overlay"><span>Now</span></div>
    </div>
    <!-- next -->
    <PresentationSlideItem
      v-if="next < slideUrls.length"
      :slideUrl="slideUrls[next].url"
    />
    <img
      v-else
      src="@/assets/presentationTemplates/last-slide.png"
      alt=""
      class="template-insert"
    />
    <!-- slider: 슬라이드 넘기기 ui -->
    <div class="d-flex justify-content-center align-items-center">
      <button class="slider-prev-button" @click="progressPrev">prev</button>
      <div class="slider-progress-indicator">
        {{ now + 1 }}/{{ slideUrls.length }}
      </div>
      <button class="slider-next-button" @click="progressNext">next</button>
    </div>
    <!-- slider: 슬라이드 넘기기 ui -->
    <!-- slider alert: 첫 슬라이드, 마지막 슬라이드 alert -->
    <transition name="fade">
      <div v-if="alertShow" class="access-alert">
        <h5 class="mb-0">{{ alertMessage }}</h5>
      </div>
    </transition>
    <!-- slider alert: 첫 슬라이드, 마지막 슬라이드 alert -->
  </div>
</template>

<script>
import PresentationSlideItem from './PresentationSlideItem.vue';
import Dummy from './Dummy.js';

export default {
  name: 'PresentationSlider.vue',
  components: { PresentationSlideItem },
  // : props
  props: {},
  // : data
  data() {
    return {
      /* 임시 데이터 */
      slideUrls: Dummy.getSlideUrls(),
      /* 임시로 data에 저장, 실 서비스에서는 state로 관리해야 함 나갔다 들어와도 그대로여야 하니까 */
      prev: -1,
      now: 0,
      next: 1,
      alertMessage: null,
      alertShow: false,
    };
  },
  // : computed
  computed: {
    presentationSize() {
      return this.$store.state.meetingRoom.size;
    },
    presentationLocation() {
      return this.$store.state.meetingRoom.location;
    },
  },
  // : watch
  watch: {
    now: function () {
      // 발표자의 현재 이미지 url state에 저장: 이미지 변경 시 -> actions / mutation으로 분리해야함
      this.$store.state.meetingRoom.nowImageUrl = this.slideUrls[this.now].url;
      // 현재 본인이 발표자라면 웹소켓 메시지 보내기
      if (
        this.$store.state.meetingRoom.presenter ===
        this.$store.state.meetingRoom.myName
      ) {
        var message = {
          id: 'changePresentation',
          imageUri: this.slideUrls[this.now].url,
          location: this.presentationLocation,
          size: this.presentationSize,
        };
        this.$store.dispatch('meetingRoom/sendMessage', message);
      }
    },
  },
  // : lifecycle hook
  mounted() {
    // 발표자의 현재 이미지 url state에 저장: 발표 시작 시
    this.$store.state.meetingRoom.nowImageUrl = this.slideUrls[this.now].url;
  },
  // : methods
  methods: {
    progressPrev: function () {
      if (this.now > 0) {
        this.prev -= 1;
        this.now -= 1;
        this.next -= 1;
      } else {
        this.alertMessage = '첫 번째 슬라이드입니다.';
        this.activeAlert();
      }
    },
    progressNext: function () {
      if (this.now < this.slideUrls.length - 1) {
        this.prev += 1;
        this.now += 1;
        this.next += 1;
      } else {
        this.alertMessage = '마지막 슬라이드입니다.';
        this.activeAlert();
      }
    },
    activeAlert: function () {
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
.progress-button {
  cursor: pointer;
}
.template-insert {
  border-radius: 25px;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  width: 100%;
  margin-bottom: 20px;
  height: auto;
}
.slider-prev-button {
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border: none;
  width: 125px;
  height: 35px;
  border-radius: 30px 0px 0px 30px;
  color: white;
  font-weight: bold;
  background: linear-gradient(90deg, #2c3153 0%, #15182a 100%);
}
.slider-next-button {
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border: none;
  width: 125px;
  height: 35px;
  border-radius: 0px 30px 30px 0px;
  color: white;
  font-weight: bold;
  background: linear-gradient(90deg, #2c3153 0%, #15182a 100%);
}
.slider-progress-indicator {
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border: none;
  width: 100px;
  height: 35px;
  color: white;
  font-weight: bold;
  background: linear-gradient(90deg, #a0b0d0 0%, #7587a6 100%);
  display: flex;
  justify-content: center;
  align-items: center;
}
.overlay {
  position: absolute;
  bottom: 0;
  border-radius: 25px;
  background: rgba(0, 0, 0, 0.7);
  width: 100%;
  height: 100%;
  opacity: 1;
  color: white;
  font-size: 2rem;
  line-height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.access-alert {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 75%;
  border-radius: 10px;
  background: rgba(255, 255, 255, 0.774);
  color: black;
  display: flex;
  justify-content: center;
  align-items: center;
  transform: translate(-50%, -50%);
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
