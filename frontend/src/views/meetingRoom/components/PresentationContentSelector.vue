<template>
  <div>
    <PresentationContentItem />
    <PresentationContentItem />
    <PresentationContentItem />
    <PresentationContentItem />
    <PresentationContentItem />
    <PresentationContentItem />
  </div>
</template>

<script>
import PresentationContentItem from './PresentationContentItem.vue';
import Dummy from './Dummy.js';

export default {
  name: 'PresentationContentSelector',
  components: { PresentationContentItem },
  // : props
  props: {},
  // : data
  data() {
    return {
      slideUrls: Dummy.getSlideUrls(),
    };
  },
  // : computed
  computed: {
    presentationContents() {
      return this.$store.state.meetingRoom.presentationContents;
    },
    roomNumber() {
      return this.$store.state.meetingRoom.roomNumber;
    },
  },
  // : lifecycle hook
  mounted() {
    // 임시
    const message = {
      id: 'changePresentation',
      imageUri: this.slideUrls[0].url,
      location: 'right',
      size: '2',
    };
    this.$store.dispatch('meetingRoom/sendMessage', message);
  },
  // : methods
  methods: {
    selectContent: function (content) {
      const slideUrls = [];
      for (let i = 1; i <= content.size; i++) {
        let filepath = `filepath/${this.roomNumber}/${content.userId}/${i}`;
        slideUrls.push(filepath);
      }
      this.$store.dispatch('meetingRoom/selectContent', content);
      // sliderUrls state에 갱신시켜주기
    },
  },
};
</script>
