<template>
  <div>
    <div :id="videoId">
    </div>
  </div>
</template>

<script>
// import "./template.scss";

export default {
  name: 'VideoUnit',
  components: {
  },
  // : props
  props: {
    participant: Object
  },
  // : data
  data() {
    return {
    }
  },
  // : computed
  computed: {
    videoId() {
      return "video-of-" + this.participant.name
    },
    video() {
      return this.participant.getVideoElement()
    }
  },
  // : lifecycle hook
  mounted() {
    document.getElementById(this.videoId).appendChild(this.video)
    console.log( this.videoId + 'mounted')
  },
  // : methods
  methods: {
  }
}
</script>