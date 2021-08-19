<template>
  <div :id="containerId" class="video-unit-container">
    <div class="overlay">
      <span>{{ participantName }}</span>
    </div>
  </div>
</template>

<script>
// import "./template.scss";
import _ from 'lodash';

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
    participantName() {
      return _.split(this.participant.name, '-')[0];
    },
  },
  // : lifecycle hook
  mounted() {
    this.video.classList.add('video-insert');
    document.getElementById(this.containerId).appendChild(this.video);
  },
  // : methods
  methods: {},
};
</script>

<style scoped>
.video-unit-container {
  position: relative;
}
.video-insert {
  height: 100%;
  border-radius: 15px;
  box-shadow: 0px 4px 4px black;
  transform: scale(-1, 1);
}
.overlay {
  position: absolute;
  bottom: 5%;
  left: 50%;
  transform: translate(-50%);
  background: rgba(0, 0, 0, 0.7);
  width: 50%;
  height: 20%;
  transition: 0.3s ease;
  opacity: 0;
  color: white;
  font-size: 1rem;
  line-height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.video-unit-container:hover .overlay {
  opacity: 1;
}
</style>
