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
  computed: {},
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
  methods: {},
};
</script>
