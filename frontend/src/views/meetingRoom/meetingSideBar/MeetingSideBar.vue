<template>
  <div class="sidebar-container d-flex flex-column p-3">
    <div class="d-flex justify-content-end">
      <button type="button" class="btn-close" aria-label="Close" @click="toggleRightSide"></button>
    </div>
    <PresentationController/>
  </div>
</template>

<script>
// import "./template.scss";
import PresentationController from './meetingSideBar/PresentationController.vue'

export default {
  name: 'MeetingSideBar',
  components: {
    PresentationController
  },
  // : props
  props: {
  },
  // : data
  data() {
    return {
    }
  },
  // : computed
  computed: {
  },
  // : lifecycle hook
  mounted() {
  },
  // : methods
  methods: {
    toggleRightSide() {
      this.$emit('toggleRightSide')
    }
  }
}
</script>

<style scoped>
  .sidebar-container {
    background: #405463;
    border-radius: 15px;
  }
</style>