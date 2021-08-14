<template>
  <!--카드 유닛-->
  <div class="col-xl-3 col-md-6 mb-xl-0 mb-4 p-3">
    <div
      v-bind:style="backgroundImg"
      class="card card-background move-on-hover background-wrap"
    >
      <div class="card-body content text-white">
        <h4 class="text-white">{{ roomInfo.name }}</h4>
        <!-- <p class="mb-2 text-sm">Room Id</p> -->
        <p class="mb-2 text-sm">{{ roomInfo.description }}</p>
        <p class="mb-2 text-sm">{{ roomInfo.startTime }}</p>

        <p class="mb-4 text-sm" v-if="isHistory">{{ roomInfo.endTime }}</p>
        <router-link
          to="/dashboard/info"
          v-if="isNow"
          class="content text-white"
        >
          JOIN ROOM
        </router-link>
        <router-link to="/dashboard/info" class="content text-white">
          Roon Info
        </router-link>
        <!-- ㅎㅇ방 번호로 api요청, Room Info->아이콘으로 교체 -->
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
  props: ['titleImg', 'idx'], //titleImg에 따라 backgroudn 변경
  data() {
    return {
      isNow: false,
      isHistory: false,
      backgroundImg:
        "background-image: url('../../assets/img/curved-images/curved14.jpg');",
      roomInfo: {},
      length: 0,
    };
  },
  created() {
    this.isNow = this.titleImg === 'Now' ? true : false;
    this.isHistory = this.titleImg === 'History' ? true : false;

    if (this.isNow) {
      this.backgroundImg =
        "background-image: url('../../assets/img/curved-images/curved10.jpg');";
      this.roomInfo = this.$store.state.rooms.now[this.idx];
    } else if (this.isFuture) {
      this.roomInfo = this.$store.state.rooms.future[this.idx];
      this.backgroundImg =
        "background-image: url('../../assets/img/curved-images/curved14.jpg');";
    } else {
      this.roomInfo = this.$store.state.rooms.history[this.idx];
      this.backgroundImg =
        "background-image: url('../../assets/img/curved-images/curved.jpg');";
    }
    console.log('RoomCard : ' + this.idx + ' ' + this.titleImg);
    console.log(this.roomInfo);
  },
  components: {},
  computed: {},
  mounted() {},
};
</script>
<style>
@import './room-style.css';
</style>
