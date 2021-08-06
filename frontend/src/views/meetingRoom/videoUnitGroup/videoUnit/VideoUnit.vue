<template>
  <div>
    <div :id="videoId"></div>
  </div>
</template>

<script>
// import "./template.scss";

export default {
  name: 'VideoUnit',
  components: {},
  // : props
  props: {
    participant: Object,
  },
  // : data
  data() {
    return {};
  },
  // : computed
  computed: {
    videoId() {
      return 'video-of-' + this.participant.name;
    },
    video() {
      return this.participant.getVideoElement();
    },
  },
  // : lifecycle hook
  mounted() {
    this.video.classList.add('video-insert');
    document.getElementById(this.videoId).appendChild(this.video);
    console.log(this.videoId + ' mounted');
  },
  // : methods
  methods: {},
};
</script>

<style>
.video-insert {
  width: 100%;
  border: white;
}
</style>
