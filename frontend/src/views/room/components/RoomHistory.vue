<template>
  <!--history-->
  <div class="row mx-0">
    <div class="col-12">
      <div class="card m-5">
        <div class="card-header pb-0 ps-5">
          <h4 class="mb-1 ps-3">
            History <i class="fas fa-chevron-down"></i
            ><i class="fas fa-chevron-up"></i>
          </h4>
        </div>
        <!--카드목록-->
        <div class="card-body px-4 ps-5 pe-9">
          <div class="row">
            <RoomCard />
            <RoomCard />
            <RoomCard />
            <RoomLast />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Vue from 'vue';
import VueAlertify from 'vue-alertify';
import RoomCard from '@/views/room/components/RoomCard';
import RoomLast from '@/views/room/components/RoomLast';

Vue.use(VueAlertify);

export default {
  name: 'RoomHistory',
  data() {
    return {};
  },
  components: {
    RoomCard,
    RoomLast,
  },
};
</script>
<style>
@import './room-style.css';
</style>
