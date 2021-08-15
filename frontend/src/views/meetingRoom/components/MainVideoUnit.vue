<template>
  <div class="main-video-container" id="main-video-container">
    <!-- main video -->
    <!-- example image -->
    <!-- <img
      src="https://images.unsplash.com/photo-1566475955255-404134a79aeb?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=967&q=80"
      alt=""
      class="video-insert img-fluid"
      id="main-video"
    /> -->
    <!-- example image -->
    <!-- example video -->
    <!--     <video
      src="@/assets/videos/pepper.mp4"
      autoplay
      class="video-insert"
      type="video/mp4"
      id="main-video"
    ></video> -->
    <!-- example video -->
    <!-- main video -->
    <!-- presentation image -->
    <!-- <div id="image-container" class="image-container"> -->
    <transition name="transition-first" v-if="nowImageUrl !== null">
      <img
        :src="this.nowImageUrl"
        :key="this.nowImageUrl"
        alt="presentation image"
        :class="[presetCss, 'img-fluid']"
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
  // : watch
  /*   watch: {
    videoY: function () {
      this.$store.state.meetingRoom.videoY = this.videoY;
      console.log(this.$store.state.meetingRoom.videoY);
    },
  }, */
  // : computed
  computed: {
    nowImageUrl: function () {
      return this.$store.state.meetingRoom.nowImageUrl;
    },
    mainVideo() {
      return this.mainParticipant.getVideoElement();
    },
    mainParticipantName() {
      return _.split(this.mainParticipant.name, '-')[0];
    },
    // presetCss: presentation-image-right-2 형태
    presetCss() {
      return (
        'presentation-image-' +
        this.$store.state.meetingRoom.location +
        '-' +
        this.$store.state.meetingRoom.size
      );
    },
    /*     mainVideoInfo: function () {
      return document.getElementById('main-video').getBoundingClientRect();
    },
    imageContainerInfo: function () {
      return document.getElementById('image-container').getBoundingClientRect();
    }, */
  },
  // : lifecycle hook
  /*   created() {
    console.log(window.innerWidth);
  }, */
  mounted() {
    this.mainVideo.classList.add('video-insert');
    document.getElementById('main-video-container').appendChild(this.mainVideo);
    /* console.log(this.mainVideoInfo); */
    // 발표자료의 너비는 영상의
    /* document.getElementById('image-container').style.width =
      String((this.mainVideoInfo['width'] / 10) * 6) + 'px'; */
    // 비디오의 y축 중앙으로 오게 하기: style 이미지 top = 비디오의 top + 비디오 height/2 - 이미지 width/2
    /* document.getElementById('image-container').style.top =
      String(this.mainVideoInfo['top'] + this.mainVideoInfo['height'] / 2) +
      'px'; */
    // 비디오의y축 중앙과  발표자료의 y축 중앙 일치시키기 오게 하기: style transfrom y축 -50% 주기
    /* document.getElementById('image-container').style.transform =
      'translate(0, -50%)'; */
    /* console.log(window.innerWidth - this.mainVideoInfo['right']); */
    // 비디오의 x축 오른쪽 라인에 발표자료 붙이기
    /* document.getElementById('image-container').style.right =
      String(window.innerWidth - this.mainVideoInfo['right']) + 'px'; */
    // 비디오의 x축 오른쪽 라인에서 발표자료의 크기보다 10% 띄어 두기
    /* document.getElementById('image-container').style.transform =
      'translate(-10%, -50%)'; */
  },
  // : methods
  methods: {},
};
</script>

<style scoped>
.main-video-container {
  position: relative;
  height: 100%;
  width: auto;
}
/* .image-container {
  position: absolute;
  width: 60%;
  border-radius: 25px;
} */
.transition-first-enter-active {
  animation: fadeIn 0.3s;
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
/* presentation preset css : 프레젠테이션 위치, 크기 프리셋 */
/* right */
.presentation-image-right-0 {
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
}
/* left */
.presentation-image-left-0 {
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
}
/* top */
.presentation-image-top-0 {
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
}
</style>

<style>
.video-insert {
  height: 100%;
  border-radius: 25px;
  box-shadow: 0px 4px 4px black;
}
</style>
