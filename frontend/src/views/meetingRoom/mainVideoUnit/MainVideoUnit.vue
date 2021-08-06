<template>
  <div>
    <div id="mainVideo"></div>
  </div>
</template>

<script>
// import "./template.scss";

export default {
  name: 'MainVideoUnit',
  components: {},
  // : props
  props: {
    mainParticipant: Object,
  },
  // : data
  data() {
    return {};
  },
  // : computed
  computed: {
    video() {
      return this.mainParticipant.getVideoElement();
    },
  },
  // : lifecycle hook
  mounted() {
    this.video.classList.add('video-insert');
    document.getElementById('mainVideo').appendChild(this.video);
  },
  // : updated
  updated() {
    console.log(this.mainParticipant);
  },
  // : methods
  methods: {},
};
</script>
