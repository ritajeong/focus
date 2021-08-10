<template>
  <div>
    <img :src="videoUrl" alt="" class="video-insert img-fluid" />
  </div>
</template>

<script>
// import "./template.scss";

export default {
  name: 'VideoUnit',
  components: {},
  // : props
  props: {
    videoUrl: String,
  },
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

<style scoped>
.video-insert {
  height: 100%;
  border-radius: 15px;
  box-shadow: 0px 4px 4px black;
}
</style>
