<template>
  <div>
    <PresentationContentItem
      v-for="content in contentList"
      :key="content.user_id"
      :content="content"
    />
  </div>
</template>

<script>
import PresentationContentItem from './PresentationContentItem.vue';

export default {
  name: 'PresentationContentSelector',
  components: { PresentationContentItem },
  // : props
  props: {},
  // : data
  data() {
    return {};
  },
  // : computed
  computed: {
    contentList() {
      return this.$store.state.meetingRoom.presentationContents;
    },
    roomNumber() {
      return this.$store.state.meetingRoom.roomNumber;
    },
  },
  // : lifecycle hook
  mounted() {},
  // : methods
  methods: {},
};
</script>
