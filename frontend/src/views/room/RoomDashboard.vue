<template>
  <div>
    <RoomGroup />
    <RoomGroup />
    <RoomGroup />
  </div>
</template>

<script>
import Vue from 'vue';
import RoomGroup from '@/views/room/components/RoomGroup';
import VueAlertify from 'vue-alertify';

Vue.use(VueAlertify);

export default {
  name: 'RoomDashboard',
  components: { RoomGroup },
};
</script>
