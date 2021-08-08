<template>
  <div class="d-flex justify-content-center">
    <button @click="toggleLeftSide">left</button>
    <button @click="toggleRightSide">right</button>
  </div>
</template>

<script>
// import "./template.scss";

export default {
  name: 'MeetingRoomController',
  components: {},
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
  methods: {
    toggleLeftSide() {
      this.$emit('toggleLeftSide');
    },
    toggleRightSide() {
      this.$emit('toggleRightSide');
    },
  },
};
</script>
