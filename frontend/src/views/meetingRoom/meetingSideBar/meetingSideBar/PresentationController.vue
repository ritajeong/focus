<template>
  <div>
    <div class="form-group">
      <label for="presenter">발표자 이름:</label>
      <input type="text" class="form-control" v-model="presenter">
      <button id="presenter-set" type="button" 
              @click="presenterSet"> 설정</button>
      <button id="start" type="button" href="#" class="btn btn-info"
              @click="start"> Start</button>
      <button id="stop" type="button" href="#" class="btn btn-info"
              @click="stop"> Stop</button>
      <button id="prev" type="button" href="#" class="btn btn-info"
              @click="prev"> Prev</button>
      <button id="next" type="button" href="#" class="btn btn-warning"
              @click="next"> Next</button>
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
  },
  // : data
  data() {
    return {
      presenter: null,
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
    presenterSet: function () {
      const message = {
        id: 'presneterSet',
        presenter: this.presenter
      }
      this.$store.dispatch('meetingRoom/sendMessage', message)
    },
    start: function () {
      const message = {
        id: 'start'
      }
      this.$store.dispatch('meetingRoom/sendMessage', message)
    },
    stop: function () {
      const message = {
        id: 'stop'
      }
      this.$store.dispatch('meetingRoom/sendMessage', message)
    },
    prev: function () {
      const message = {
        id: 'prev'
      }
      this.$store.dispatch('meetingRoom/sendMessage', message)
    },
    next: function () {
      const message = {
        id: 'next'
      }
      this.$store.dispatch('meetingRoom/sendMessage', message)
    }
  }
}
</script>