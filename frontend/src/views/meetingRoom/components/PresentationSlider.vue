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
    <div class="d-flex justify-content-around align-items-center">
      <img
        src="@/assets/icons/prev.svg"
        alt="previous slide"
        class="progress-button"
        @click="progressPrev"
      />
      <img
        src="@/assets/icons/next.svg"
        alt="next slide"
        class="progress-button"
        @click="progressNext"
      />
    </div>
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
      }
    },
    progressNext: function () {
      if (this.now < this.slideUrls.length - 1) {
        this.prev += 1;
        this.now += 1;
        this.next += 1;
      }
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
</style>
