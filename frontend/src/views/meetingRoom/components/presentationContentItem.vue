<template>
  <div>
    <div class="content-container">
      <img
        src="https://upload.wikimedia.org/wikipedia/commons/7/7c/Aspect_ratio_16_9_example.jpg"
        alt=""
        class="content-insert img-fluid"
      />
      <div class="overlay"><span>Roomname/Username/Filename</span></div>
    </div>
  </div>
</template>

<script>
// import "./template.scss";

export default {
  name: 'PresentationContentItem',
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
  methods: {},
};
</script>

<style scoped>
.content-container {
  position: relative;
  width: 100%;
  height: auto;
  margin-bottom: 20px;
  cursor: pointer;
}
.content-insert {
  border-radius: 25px;
  box-shadow: 0px 4px 4px black;
  width: 100%;
  height: auto;
}
.overlay {
  position: absolute;
  bottom: 0;
  border-radius: 25px;
  background: rgba(0, 0, 0, 0.7); /* Black see-through */
  width: 100%;
  height: 100%;
  transition: 0.3s ease;
  opacity: 0;
  color: white;
  font-size: 1rem;
  line-height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.content-container:hover .overlay {
  opacity: 1;
}
</style>
