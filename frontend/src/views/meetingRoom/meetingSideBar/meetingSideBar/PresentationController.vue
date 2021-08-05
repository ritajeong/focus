<template>
  <div>
    <div class="form-group">
      <label for="presenter">발표자 이름:</label>
      <input type="text" class="form-control" id="presenter">
      <button id="presenter-set" type="button" 
              onclick="presenterSet()"> 설정</button>
      <button id="start" type="button" href="#" class="btn btn-info"
              onclick="start(); return false;"> Start</button>
      <button id="stop" type="button" href="#" class="btn btn-info"
              onclick="stop(); return false;"> Stop</button>
      <button id="prev" type="button" href="#" class="btn btn-info"
              onclick="prev(); return false;"> Prev</button>
      <button id="next" type="button" href="#" class="btn btn-warning"
              onclick="next(); return false;"> Next</button>
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
  }
}
</script>