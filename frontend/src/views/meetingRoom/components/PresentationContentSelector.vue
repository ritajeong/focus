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
import PresentationContentItem from './presentationContentItem.vue';

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
  computed: {},
  // : lifecycle hook
  mounted() {},
  // : methods
  methods: {},
};
</script>
