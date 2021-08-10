<template>
  <div :id="containerId"></div>
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
    video() {
      return this.participant.getVideoElement();
    },
    containerId() {
      return 'video-' + this.participant.name + '-container';
    },
  },
  // : lifecycle hook
  mounted() {
    console.log(this.participant);
    console.log(this.video);
    console.log(this.containerId);
    this.video.classList.add('video-insert');
    document.getElementById(this.containerId).appendChild(this.video);
  },
  // : methods
  methods: {},
};
</script>

<style scoped>
.video-insert {
  height: 100%;
  border-radius: 15px;
  box-shadow: 0px 4px 4px black;
}
</style>
