<template>
  <div class="d-flex flex-column justify-content-center align-items-center">
    <img
      src="@/assets/icons/up.svg"
      alt=""
      class="up-button"
      @click="onPrevVideos"
    />
    <VideoUnit
      class="video-container"
      v-for="videoUrl in videoUrls"
      :key="videoUrl.id"
      :videoUrl="videoUrl.url"
      v-show="first <= videoUrl.id && videoUrl.id <= last"
    />
    <img
      src="@/assets/icons/down.svg"
      alt=""
      class="down-button"
      @click="onNextVideos"
    />
  </div>
</template>

<script>
import VideoUnit from './VideoUnit.vue';
import Dummy from './Dummy.js';

export default {
  name: 'VideoUnitGroup',
  components: { VideoUnit },
  // : props
  props: {},
  // : data
  data() {
    return {
      /* 임시 데이터: videoUrls */
      videoUrls: Dummy.getVideoUrls(),
      first: 1,
      last: 4,
    };
  },
  // : computed
  computed: {},
  // : lifecycle hook
  mounted() {},
  // : methods
  methods: {
    onPrevVideos: function () {
      this.first -= 1;
      this.last -= 1;
    },
    onNextVideos: function () {
      this.first += 1;
      this.last += 1;
    },
  },
};
</script>

<style scoped>
.video-container {
  height: 20%;
  width: auto;
  padding: 5px 5px;
}
.up-button {
  position: absolute;
  top: 50px;
  left: 50%;
  transform: translate(-50%);
  cursor: pointer;
}
.down-button {
  position: absolute;
  left: 50%;
  bottom: 50px;
  transform: translate(-50%);
  cursor: pointer;
}
.upslide-enter-active {
  animation: slideInUp 0.3s;
}
.upslide-leave-active {
  animation: slideOutUp 0.3s;
}
</style>
