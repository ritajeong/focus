<template>
  <div class="video-group-container d-flex flex-column p-3">
    <div class="d-flex justify-content-end">
      <button type="button" class="btn-close" aria-label="Close" @click="toggleLeftSide"></button>
    </div>
    <div v-for="participant in participants" :key="participant.name">
      <VideoUnit class="video-unit" :participant="participant"/>
    </div>
  </div>
</template>

<script>
// import "./template.scss";
import VideoUnit from './videoUnit/VideoUnit.vue'

export default {
  name: 'VideoUnitGroup',
  components: {
    VideoUnit,
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
    participants() {
      return this.$store.state.meetingRoom.participants
    }
  },
  // : lifecycle hook
  mounted() {
  },
  // : methods
  methods: {
    toggleLeftSide() {
      this.$emit('toggleLeftSide')
    }
  }
}
</script>

<style scoped>
  .video-unit {
    height: 20%;
  }
  .video-group-container {
    background: #405463;
    border-radius: 15px;
  }
</style>