<template>
  <div>
    <RoomGroup v-bind:title="now" />
    <RoomGroup v-bind:title="future" />
    <RoomGroup v-bind:title="history" />
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
  data() {
    return {
      now: 'Now',
      future: 'Future',
      history: 'History',
    };
  },
  created() {
    this.$store.dispatch(
      'rooms/fetchRooms',
      this.$store.state.users.login.userid,
    );

    const url = 'wss://' + location.host + '/groupcall';
    this.$store.dispatch('meetingRoom/wsInit', url);
  }, //axios
};
</script>
