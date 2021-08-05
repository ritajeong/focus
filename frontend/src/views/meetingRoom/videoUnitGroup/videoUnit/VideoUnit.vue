<template>
  <div>
    <div :id="videoId">
    </div>
  </div>
</template>

<script>
// import "./template.scss";

export default {
  name: 'Template',
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
      return "video-" + this.participant.name
    },
    video() {
      return this.participant.getVideoElement()
    }
  },
  // : lifecycle hook
  mounted() {
    document.getElementById(this.videoId).appendChild(this.video)
    console.log('video' + this.videoId + 'mounted')
  },
  // : methods
  methods: {
  }
}
</script>