<template>
  <div class="d-flex flex-column justify-content-center align-items-center">
    <div class="template-container">
      <img
        src="@/assets/presentationTemplates/presentation-left.jpg"
        alt=""
        class="template-insert img-fluid"
      />
      <div class="overlay"><span>Presentation On Left</span></div>
    </div>
    <div class="template-container">
      <img
        src="@/assets/presentationTemplates/presentation-right.jpg"
        alt=""
        class="template-insert img-fluid"
      />
      <div class="overlay"><span>Presentation On Right</span></div>
    </div>
    <div class="template-container">
      <img
        src="@/assets/presentationTemplates/presentation-top.jpg"
        alt=""
        class="template-insert img-fluid"
      />
      <div class="overlay"><span>Presentation On Top</span></div>
    </div>
    <div class="size-controller">
      <input type="range" class="range-select" min="0" max="5" step="1" />
      <h2 class="text-center">size</h2>
    </div>
  </div>
</template>

<script>
// import "./template.scss";

export default {
  name: 'PresentationLocationSelector',
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
.template-container {
  position: relative;
  width: 240px;
  height: 180px;
  margin-bottom: 20px;
  cursor: pointer;
}
.template-insert {
  border-radius: 25px;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  width: 240px;
  height: 180px;
}
.overlay {
  position: absolute;
  bottom: 0;
  border-radius: 25px;
  background: rgba(255, 255, 255, 0.8);
  width: 240px;
  height: 180px;
  transition: 0.3s ease;
  opacity: 0;
  color: black;
  font-size: 1.2rem;
  font-weight: bold;
  display: flex;
  justify-content: center;
  align-items: center;
}
.template-container:hover .overlay {
  opacity: 1;
}
.size-controller {
  margin-top: 50px;
}
.range-select {
  -webkit-appearance: none;
  appearance: none;
  width: 240px;
  height: 15px;
  border-radius: 5px;
  background: #d3d3d3;
  outline: none;
  opacity: 0.7;
  -webkit-transition: 0.2s;
  transition: opacity 0.2s;
}
.range-select:hover {
  opacity: 1;
}
.range-select::-webkit-slider-thumb {
  -webkit-appearance: none;
  appearance: none;
  width: 25px;
  height: 25px;
  border-radius: 50%;
  background: #15182a;
  cursor: pointer;
}
.range-select::-moz-range-thumb {
  width: 25px;
  height: 25px;
  border-radius: 50%;
  background: #15182a;
  cursor: pointer;
}
</style>
