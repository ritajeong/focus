<template>
  <div class="slide-container">
    <img :src="slideUrl" alt="" class="slide-insert img-fluid" />
  </div>
</template>

<script>
// import "./template.scss";

export default {
  name: 'PresentationSlideItem',
  components: {},
  // : props
  props: {
    slideUrl: String,
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
.slide-container {
  position: relative;
  width: 100%;
  height: auto;
  margin-bottom: 20px;
}
.slide-insert {
  border-radius: 25px;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  width: 100%;
  height: auto;
}
</style>
