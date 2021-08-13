<template>
  <!--now-->
  <div class="row mx-0">
    <div class="col-12">
      <div class="card m-5">
        <div class="card-header pb-0 ps-5">
          <!--props값에 따라 헤더 출력 [Now, Future, History]-->
          <h4 class="mb-1 ps-3">
            {{ title }}<i class="fas fa-chevron-down"></i
            ><i class="fas fa-chevron-up"></i>
          </h4>
        </div>
        <!-- icon 클릭하면 하단의 카드목록을 접고 닫기 v-show(v-if X) 혹은 css checked-->
        <!--카드목록-->
        <div class="card-body ps-5 pe-10">
          <div class="row">
            <!--props값 now이면 cardgroup[0]에 RoomNew-->
            <!-- 공통 : 마지막 원소 cardgroup.length-1자리에 RoomLast-->
            <!-- 리스트없으면 공란 ? 또는 안내말 쓰기-->
            <!-- v-for -->
            <RoomNew v-show="isNow" />
            <RoomCard v-bind:titleImg="titleImg" />
            <RoomCard v-bind:titleImg="titleImg" />
            <RoomLast v-bind:titleImg="titleImg" />
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
  name: 'RoomGroup',
  props: ['title'],
  data() {
    return {
      titleImg: this.title, //부모(RoomDashboard)부터 받은 title을 다시 자식인 RoomCard로 보냄
      isNow: false,
      isFuture: false,
      isHistory: false,
    };
  },
  created() {
    if (this.title === 'Now') this.isNow = true;
    else if (this.title === 'Future') this.isFuture = true;
    else this.isHistory = true;
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
