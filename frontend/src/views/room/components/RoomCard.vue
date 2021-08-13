<template>
  <!--카드 유닛-->
  <div class="col-xl-3 col-md-6 mb-xl-0 mb-4 px-3">
    <div
      v-bind:style="backgroundImg"
      class="card card-background move-on-hover background-wrap"
    >
      <div class="card-body content text-white">
        <h4 class="text-white">Room Name</h4>
        <p class="mb-2 text-sm">Room Id</p>
        <p class="mb-4 text-sm">Room Description</p>
        <router-link
          to="/dashboard/info"
          v-if="isNow"
          class="content text-white"
        >
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
  props: ['titleImg'], //titleImg에 따라 backgroudn 변경
  data() {
    return {
      isNow: false,
      backgroundImg:
        "background-image: url('../../assets/img/curved-images/curved14.jpg');",
    };
  },
  created() {
    console.log('RoomCard : ' + this.titleImg);
    this.isNow = this.titleImg === 'Now' ? true : false;
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
  components: {},
};
</script>
<style>
@import './room-style.css';
</style>
