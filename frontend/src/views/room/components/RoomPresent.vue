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
        <div class="card-body px-4 ps-5 pe-9">
          <div class="row">
            <RoomNew />
            <RoomNow />
            <RoomNow />
            <RoomNow />
            <RoomMore />
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
import RoomNew from '@/views/room/components/RoomNew';
import RoomNow from '@/views/room/components/RoomNow';

import RoomMore from '@/views/room/components/RoomMore';

Vue.use(VueAlertify);

export default {
  name: 'RoomPresent',
  data() {
    return {};
  },
  components: {
    RoomNew,
    RoomNow,
    RoomMore,
  },
};
</script>
<style>
@import './room-style.css';
</style>
