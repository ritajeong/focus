<template>
  <div class="d-flex flex-column justify-content-center align-items-center">
    <a href=""><img src="@/assets/icons/up.svg" alt="" class="up-button" /></a>
    <VideoUnit class="video-container" />
    <VideoUnit class="video-container" />
    <VideoUnit class="video-container" />
    <VideoUnit class="video-container" />
    <a href=""
      ><img src="@/assets/icons/down.svg" alt="" class="down-button"
    /></a>
  </div>
</template>

<script>
import VideoUnit from './VideoUnit.vue';

export default {
  name: 'VideoUnitGroup',
  components: { VideoUnit },
  // : props
  props: {},
  // : data
  data() {
    return {};
  },
  // : computed
  computed: {},
  // : lifecycle hook
  mounted() {},
  // : methods
  methods: {},
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
}
.down-button {
  position: absolute;
  left: 50%;
  bottom: 50px;
  transform: translate(-50%);
}
</style>
