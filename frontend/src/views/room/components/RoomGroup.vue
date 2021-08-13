<template>
  <!--now-->
  <div class="row mx-0">
    <div class="col-12">
      <div class="card m-5">
        <div class="card-header pb-0 ps-5">
          <h4 class="mb-1 ps-3">
            Now <i class="fas fa-chevron-down"></i
            ><i class="fas fa-chevron-up"></i>
          </h4>
        </div>
        <!--카드목록-->
        <div class="card-body ps-5 pe-10">
          <div class="row">
            <RoomNew />
            <RoomCard />
            <RoomCard />
            <RoomLast />
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--now-->
</template>
<script>
import Vue from 'vue';
import VueAlertify from 'vue-alertify';
import RoomCard from '@/views/room/components/RoomCard';
import RoomLast from '@/views/room/components/RoomLast';
import RoomNew from '@/views/room/components/RoomNew';

Vue.use(VueAlertify);

export default {
  name: 'RoomPresent',
  data() {
    return {};
  },
  components: {
    RoomCard,
    RoomLast,
    RoomNew,
  },
};
</script>
<style>
@import './room-style.css';
</style>
