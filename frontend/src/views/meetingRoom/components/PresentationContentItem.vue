<template>
  <div>
    <div
      :class="[
        { 'container-border': content.user_id === selectedContentId },
        'content-container',
      ]"
    >
      <img :src="firstImgUrl" alt="" class="content-insert img-fluid" />
      <div class="overlay" @click="selectContent()">
        <span>{{ contentUserName }}님의 발표자료</span>
      </div>
    </div>
  </div>
</template>

<script>
// import "./template.scss";
import _ from 'lodash';

const FILE_PATH = 'https://i5a107.p.ssafy.io:8446/board/image';
// const FILE_PATH = '/home/ubuntu/presentations';

export default {
  name: 'PresentationContentItem',
  components: {},
  // : props
  props: {
    content: Object,
  },
  // : data
  data() {
    return {};
  },
  // : computed
  computed: {
    roomNumber() {
      return this.$store.state.meetingRoom.roomNumber;
    },
    firstImgUrl() {
      return `${FILE_PATH}/${this.roomNumber}/${this.content.user_id}/1`;
    },
    /*     imageSize() {
      return this.$store.state.meetingRoom.size;
    },
    imageLocation() {
      return this.$store.state.meetingRoom.location;
    }, */
    selectedContentId() {
      return this.$store.state.meetingRoom.selectedContentId;
    },
    // 파일의 주인 유저 이름을 가져오기
    participants() {
      return this.$store.state.meetingRoom.participants;
    },
    contentUserName() {
      const userName = Object.keys(this.participants).find(
        userName => _.split(userName, '-')[1] == this.content.user_id,
      );
      return _.split(userName, '-')[0];
    },
  },
  // : lifecycle hook
  mounted() {},
  // :    methods
  methods: {
    selectContent: function () {
      // 이미지 url 목록 만들어서 state에 저장
      /* const imageUrls = [];
      for (let i = 1; i <= this.content.file_size; i++) {
        let filepath = `${FILE_PATH}/${this.roomNumber}/${this.content.user_id}/${i}`;
        imageUrls.push(filepath);
      }
      this.$store.dispatch('meetingRoom/setImageUrls', imageUrls); */
      // 선택된 content의 user_id를 ContentId로 지정해 state에 저장
      /* this.$store.dispatch(
        'meetingRoom/setSelectedContentId',
        this.content.user_id,
      ); */
      // websocket에 첫 번째 이미지에 대한 메시지 보내주기
      /* const size = this.imageSize === null ? '2' : this.imageSize;
      const location =
        this.imageLocation === null ? 'right' : this.imageLocation;
      const message = {
        id: 'changePresentation',
        imageUri: this.firstImgUrl,
        location: location,
        size: size,
      };
      this.$store.dispatch('meetingRoom/sendMessage', message); */
      // sliderUrls state에 갱신시켜주기
      // Websocket에 Content 변경 정보 보내주기
      const message = {
        id: 'changeContent',
        presentationUserId: this.content.user_id,
      };
      this.$store.dispatch('meetingRoom/sendMessage', message);
    },
  },
};
</script>

<style scoped>
.content-container {
  position: relative;
  width: 100%;
  height: auto;
  border-radius: 25px;
  margin-bottom: 20px;
  cursor: pointer;
}
.content-insert {
  border-radius: 25px;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  width: 100%;
  height: auto;
}
.container-border {
  border: 0.4rem solid;
}
.overlay {
  position: absolute;
  bottom: 0;
  border-radius: 25px;
  background: rgba(0, 0, 0, 0.7);
  width: 100%;
  height: 100%;
  transition: 0.3s ease;
  opacity: 0;
  color: white;
  font-size: 1rem;
  line-height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.content-container:hover .overlay {
  opacity: 1;
}
</style>
