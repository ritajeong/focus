<template>
  <div>
    <RoomPresent />
    <RoomFuture />
    <RoomHistory />
    <AppCard />
  </div>
</template>

<script>
import Vue from 'vue';
import RoomPresent from '@/views/room/components/RoomPresent';
import RoomFuture from '@/views/room/components/RoomFuture';
import RoomHistory from '@/views/room/components/RoomHistory';
import AppCard from '@/components/common/AppCard.vue';
import VueAlertify from 'vue-alertify';

Vue.use(VueAlertify);

export default {
  name: 'RoomDashboard',
  components: { AppCard, RoomPresent, RoomFuture, RoomHistory },
};
</script>
