<template>
  <!--카드 유닛-->
  <div class="col-xl-3 col-md-6 mb-xl-0 mb-4 px-3">
    <div
      class="card card-background move-on-hover background-wrap"
      style="
        background-image: url('../../assets/img/curved-images/curved10.jpg');
      "
    >
      <div class="card-body content text-white">
        <h4 class="text-white">Room Name</h4>
        <p class="mb-2 text-sm">Room Id</p>
        <p class="mb-4 text-sm">Room Description</p>
        <router-link to="/dashboard/info" class="content text-white">
          JOIN ROOM
        </router-link>
      </div>
    </div>
  </div>
  <!--카드 유닛-->
</template>
<script>
import Vue from 'vue';
import VueAlertify from 'vue-alertify';

Vue.use(VueAlertify);

export default {
  name: 'RoomCard',
  data() {
    return {};
  },
  components: {},
};
</script>
<style>
@import './room-style.css';
</style>
