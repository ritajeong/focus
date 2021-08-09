<template>
  <svg :x="x" :y="y" :width="width" :height="height" :viewBox="viewBox">
    <path :fill="color" :d="d" />
  </svg>
</template>

<script>
export default {
  name: 'BaseSvgTypePath',
  props: {
    x: {
      type: String,
      default: '0',
    },
    y: {
      type: String,
      default: '0',
    },
    width: {
      type: String,
      default: '20',
    },
    height: {
      type: String,
      default: '20',
    },
    viewBox: {
      type: String,
      default: '0 0 20 20',
    },
    color: {
      type: String,
    },
    d: {
      type: String,
      default: '',
    },
  },
};
</script>
