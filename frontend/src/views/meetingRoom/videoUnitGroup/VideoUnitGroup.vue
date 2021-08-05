<template>
  <div>
    <div v-for="participant in participants" :key="participant.name">
      <VideoUnit :participant="participant"/>
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
  }
}
</script>