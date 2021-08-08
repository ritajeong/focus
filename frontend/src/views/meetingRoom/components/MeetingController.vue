<template>
  <div class="d-flex controller">
    <button class="controller-button mx-3">버튼1</button>
    <button class="controller-button mx-3">버튼2</button>
    <button class="controller-button mx-3">버튼3</button>
    <button class="controller-button-disabled mx-3">버튼4</button>
  </div>
</template>

<script>
// import "./template.scss";

export default {
  name: 'MeetingController',
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
.controller-button {
  background: linear-gradient(90deg, #f10488 0%, #a51bb2 100%);
  box-shadow: 0px 4px 4px black;
  border: none;
  width: 150px;
  height: 40px;
  border-radius: 30px;
  color: white;
  font-weight: bold;
}
.controller-button-disabled {
  background: linear-gradient(90deg, #a0b0d0 0%, #7587a6 100%);
  box-shadow: 0px 4px 4px black;
  border: none;
  width: 150px;
  height: 40px;
  border-radius: 30px;
  color: white;
  font-weight: bold;
}
</style>
