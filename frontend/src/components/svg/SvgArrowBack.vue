<template>
  <base-svg-type-path
    :x="x"
    :y="y"
    :width="width"
    :height="height"
    :viewBox="viewBox"
    :color="color"
    d="M5.30404233,9 L17.4693579,9 L17.4693579,11 L5.39353346,11 L10.5151633,16.1216299 L9.22652416,17.4102691 L3.18578644,11.3695313 C2.40473785,10.5884828 2.40473785,9.3221528 3.18578644,8.54110422 L9.22689066,2.5 L10.5154665,3.78857584 L5.30404233,9 Z"
  />
</template>

<script>
import BaseSvgTypePath from './baseSvg/BaseSvgTypePath.vue';

export default {
  name: 'SvgArrowBack',
  components: {
    BaseSvgTypePath,
  },
  props: {
    x: {
      type: String,
    },
    y: {
      type: String,
    },
    width: {
      type: String,
    },
    height: {
      type: String,
    },
    viewBox: {
      type: String,
    },
    color: {
      type: String,
    },
    d: {
      type: String,
    },
  },
};
</script>
