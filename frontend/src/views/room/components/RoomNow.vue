<template>
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
        <p class="mb-2 text-sm">Room Description</p>
        <p class="mb-2" data-bs-toggle="modal" data-bs-target="#RoomReadyModal">
          JOIN ROOM
        </p>
        <router-link to="/dashboard/info" class="content text-white">
          ROOM INFO
        </router-link>
      </div>
    </div>
    <!-- Modal -->
    <RoomReadyModal></RoomReadyModal>
  </div>
</template>
<script>
import Vue from 'vue';
import VueAlertify from 'vue-alertify';
import RoomReadyModal from './RoomReadyModal.vue';
Vue.use(VueAlertify);

export default {
  name: 'RoomNow',
  components: { RoomReadyModal },
  data() {
    return {};
  },
};
</script>
<style>
@import './room-style.css';
</style>
