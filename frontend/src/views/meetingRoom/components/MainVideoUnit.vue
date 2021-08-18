<template>
  <div
    :class="[locationPreset, 'main-video-container']"
    id="main-video-container"
  >
    <transition name="transition-fade" v-if="currentPage !== null">
      <img
        :src="imageSrcs[currentPage]"
        :key="imageSrcs[currentPage]"
        alt="presentation image"
        :class="[sizePreset, 'img-fluid']"
        id="presentation-image"
      />
    </transition>

    <div class="overlay">
      <span>{{ mainParticipantName }}</span>
    </div>
  </div>
  <!-- presentation image -->
  <!-- </div> -->
</template>

<script>
// import "./template.scss";
import _ from 'lodash';

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
    imageSrcs: function () {
      return this.$store.state.meetingRoom.imageSrcs;
    },
    currentPage: function () {
      return this.$store.state.meetingRoom.currentPage;
    },
    mainVideo() {
      return this.mainParticipant.getVideoElement();
    },
    mainParticipantName() {
      return _.split(this.mainParticipant.name, '-')[0];
    },
    // locationPreset, sizePreset
    locationPreset() {
      return 'presentation-' + this.$store.state.meetingRoom.location;
    },
    sizePreset() {
      return 'presentation-' + this.$store.state.meetingRoom.size;
    },
    /* presetCss() {
      return (
        'presentation-image-' +
        this.$store.state.meetingRoom.location +
        '-' +
        this.$store.state.meetingRoom.size
      );
    }, */
  },
  mounted() {
    this.mainVideo.classList.add('video-insert');
    document.getElementById('main-video-container').appendChild(this.mainVideo);
  },
  // : methods
  methods: {},
};
</script>

<style scoped>
/* .main-video-container {
  position: relative;
  height: 100%;
  width: auto;
} */
.main-video-container {
  position: relative;
  height: 100%;
  width: auto;
  display: flex;
  overflow: hidden;
}
.overlay {
  position: absolute;
  bottom: 5%;
  left: 50%;
  transform: translate(-50%);
  background: rgba(0, 0, 0, 0.7);
  width: 20%;
  height: 7%;
  transition: 0.3s ease;
  opacity: 0;
  color: white;
  font-size: 1.5rem;
  line-height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.main-video-container:hover .overlay {
  opacity: 1;
}
/* location presets */
.presentation-right {
  justify-content: flex-end;
  align-items: center;
}
.presentation-left {
  justify-content: start;
  align-items: center;
}
.presentation-top {
  justify-content: center;
  align-items: start;
}
/* size presets */
.presentation-0 {
  position: absolute;
  margin: 2%;
  height: 30%;
}
.presentation-1 {
  position: absolute;
  margin: 2%;
  height: 40%;
}
.presentation-2 {
  position: absolute;
  margin: 2%;
  height: 50%;
}
.presentation-3 {
  position: absolute;
  margin: 2%;
  height: 60%;
}
.presentation-4 {
  position: absolute;
  top: 0;
  height: 100%;
  border-radius: 25px;
}
/* presentation preset css : 프레젠테이션 위치, 크기 프리셋 */
/* right */
/* .presentation-image-right-0 {
  position: absolute;
  right: 2%;
  top: 50%;
  height: 30%;
  transform: translate(0, -50%);
}
.presentation-image-right-1 {
  position: absolute;
  right: 2%;
  top: 50%;
  height: 40%;
  transform: translate(0, -50%);
}
.presentation-image-right-2 {
  position: absolute;
  right: 2%;
  top: 50%;
  height: 50%;
  transform: translate(0, -50%);
}
.presentation-image-right-3 {
  position: absolute;
  right: 2%;
  top: 50%;
  height: 60%;
  transform: translate(0, -50%);
}
.presentation-image-right-4 {
  position: absolute;
  top: 0;
  height: 100%;
  border-radius: 25px;
} */
/* left */
/* .presentation-image-left-0 {
  position: absolute;
  left: 2%;
  top: 50%;
  height: 30%;
  transform: translate(0, -50%);
}
.presentation-image-left-1 {
  position: absolute;
  left: 2%;
  top: 50%;
  height: 40%;
  transform: translate(0, -50%);
}
.presentation-image-left-2 {
  position: absolute;
  left: 2%;
  top: 50%;
  height: 50%;
  transform: translate(0, -50%);
}
.presentation-image-left-3 {
  position: absolute;
  left: 2%;
  top: 50%;
  height: 60%;
  transform: translate(0, -50%);
}
.presentation-image-left-4 {
  position: absolute;
  top: 0;
  height: 100%;
  border-radius: 25px;
} */
/* top */
/* .presentation-image-top-0 {
  position: absolute;
  left: 50%;
  top: 3%;
  height: 30%;
  transform: translate(-50%);
}
.presentation-image-top-1 {
  position: absolute;
  left: 50%;
  top: 3%;
  height: 40%;
  transform: translate(-50%);
}
.presentation-image-top-2 {
  position: absolute;
  left: 50%;
  top: 3%;
  height: 50%;
  transform: translate(-50%);
}
.presentation-image-top-3 {
  position: absolute;
  left: 50%;
  top: 3%;
  height: 60%;
  transform: translate(-50%);
}
.presentation-image-top-4 {
  position: absolute;
  top: 0;
  height: 100%;
  border-radius: 25px;
} */
/* transitions */
.transition-fade-enter-active {
  animation: flipInX 0.7s;
}
</style>

<style>
.video-insert {
  height: 100%;
  border-radius: 25px;
  box-shadow: 0px 4px 4px black;
}
</style>
