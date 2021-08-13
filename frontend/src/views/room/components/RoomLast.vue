<template>
  <!-- RoomLast일 경우 카드 최상위 부모에 card-last-parrent 클래스 추가-->
  <!-- RoomMore 에 card-last-->
  <div class="col-xl-3 col-md-6 mb-xl-0 mb-4 px-3 card-last-parrent">
    <div
      v-bind:style="backgroundImg"
      class="card card-background move-on-hover background-wrap"
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
    <!--more분리-->
    <RoomMore />
  </div>
</template>
<script>
import Vue from 'vue';
import VueAlertify from 'vue-alertify';
import RoomMore from '@/views/room/components/RoomMore';

Vue.use(VueAlertify);

export default {
  name: 'RoomLast',
  props: ['titleImg'],
  data() {
    return {
      backgroundImg:
        "background-image: url('../../assets/img/curved-images/curved14.jpg');",
    };
  },
  created() {
    console.log('RoomCard : ' + this.titleImg);
    if (this.titleImg === 'Now')
      this.backgroundImg =
        "background-image: url('../../assets/img/curved-images/curved10.jpg');";
    else if (this.titleImg === 'Future')
      this.backgroundImg =
        "background-image: url('../../assets/img/curved-images/curved14.jpg');";
    else
      this.backgroundImg =
        "background-image: url('../../assets/img/curved-images/curved.jpg');";
  },
  components: { RoomMore },
};
</script>
<style>
@import './room-style.css';
</style>
