<template>
  <div class="d-flex flex-column justify-content-center align-items-center">
    <div
      v-for="effect in effects"
      :class="[
        { 'container-border': currentEffect === effect },
        'effect-container',
      ]"
      :key="effect"
      :id="effect"
      @click="selectEffect(effect)"
      @mouseenter="showExample(effect)"
    >
      <h4>{{ effect }}</h4>
    </div>
  </div>
</template>

<script>
// import "./template.scss";

export default {
  name: 'Template',
  components: {},
  // : props
  props: {},
  // : data
  data() {
    return {
      effects: [
        'fadein',
        'fadedown',
        'fadeleft',
        'faderight',
        'fadeup',
        'backdown',
        'backup',
        'flipx',
        'flipy',
        'rotatein',
      ],
    };
  },
  // : computed
  computed: {
    messageData() {
      const data = {
        id: 'changePresentation',
        currentPage: this.$store.state.meetingRoom.currentPage,
        location: this.$store.state.meetingRoom.location,
        size: this.$store.state.meetingRoom.size,
      };
      return data;
    },
    currentEffect() {
      return this.$store.state.meetingRoom.transition;
    },
  },
  // : lifecycle hook
  mounted() {},
  // : methods
  methods: {
    selectEffect(effect) {
      const message = {
        ...this.messageData,
        transition: effect,
      };
      this.$store.dispatch('meetingRoom/sendMessage', message);
    },
    showExample(effect) {
      const el = document.getElementById(effect);
      el.classList.add(effect);
      setTimeout(function () {
        el.classList.remove(effect);
      }, 1000);
    },
  },
};
</script>

<style scoped>
.effect-container {
  position: relative;
  cursor: pointer;
  margin-bottom: 20px;
  width: 240px;
  height: 135px;
  background: white;
  border-radius: 15px;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
.container-border {
  border: 0.4rem solid;
}
.fadein {
  animation: fadeIn 0.7s;
}
.fadedown {
  animation: fadeInDown 0.7s;
}
.fadeleft {
  animation: fadeInLeft 0.7s;
}
.faderight {
  animation: fadeInRight 0.7s;
}
.fadeup {
  animation: fadeInUp 0.7s;
}
.backdown {
  animation: backInDown 0.7s;
}
.backup {
  animation: backInUp 0.7s;
}
.flipx {
  animation: flipInX 0.7s;
}
.flipy {
  animation: flipInY 0.7s;
}
.rotatein {
  animation: rotateIn 0.7s;
}
</style>
