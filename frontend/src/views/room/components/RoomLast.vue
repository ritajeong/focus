<template>
  <div class="col-xl-3 col-md-6 mb-xl-0 mb-4 px-3 card-last-parrent">
    <div class="card card-background move-on-hover background-wrap">
      <div class="card-body content text-white">
        <h4 class="text-white">Room Name</h4>
        <p class="mb-2 text-sm">Room Id</p>
        <p class="mb-4 text-sm">Room Description</p>
        <router-link to="/dashboard/info" class="content text-white">
          JOIN ROOM
        </router-link>
      </div>
    </div>
    <div class="card-last text-center">
      <a href="#"
        ><h2><i class="fas fa-plus"></i></h2>
        <h4>more</h4></a
      >
    </div>
  </div>
</template>
<script>
import Vue from 'vue';
import VueAlertify from 'vue-alertify';

Vue.use(VueAlertify);

export default {
  name: 'RoomLast',
  data() {
    return {};
  },
  components: {},
};
</script>
<style>
@import './room-style.css';
</style>
