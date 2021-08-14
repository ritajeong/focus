<template>
  <!--now-->
  <div class="row mx-0">
    <div class="col-12">
      <div class="card m-5">
        <div class="card-header pb-0 ps-5">
          <!--props값에 따라 헤더 출력 [Now, Future, History]-->
          <h4 class="mb-1 ps-3">
            {{ title
            }}<i
              class="fas fa-chevron-down ps-3"
              v-show="isToggle"
              @click="showToggle"
            ></i
            ><i
              class="fas fa-chevron-up ps-3"
              @click="showToggle"
              v-show="!isToggle"
            ></i>
          </h4>
        </div>
        <!-- icon 클릭하면 하단의 카드목록을 접고 닫기 v-show(v-if X) 혹은 css checked-->
        <!--카드목록-->
        <div class="card-body ps-5 pe-10" v-show="isToggle">
          <div class="row">
            <RoomNew v-show="isNow" />
            <!-- 공통 : 마지막 원소 cardgroup.length-1자리에 RoomLast-->
            <!-- 리스트없으면 공란 ? 또는 안내말 쓰기, more버튼 없어야함-->
            <!-- v-for -->
            <RoomCard
              v-bind:titleImg="titleImg"
              v-bind:idx="idx"
              v-for="(room, idx) in showRooms"
              :key="idx"
              v-show="idx <= showIdx"
            />

            <!-- <RoomLast v-bind:titleImg="titleImg" /> -->
            <!-- more아이콘-->
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
// import RoomLast from '@/views/room/components/RoomLast';
import RoomNew from '@/views/room/components/RoomNew';

Vue.use(VueAlertify);

export default {
  name: 'RoomGroup',
  props: ['title'],
  components: {
    RoomCard,
    // RoomLast,
    RoomNew,
  },
  data() {
    return {
      isToggle: true,
      titleImg: this.title, //부모(RoomDashboard)부터 받은 title을 다시 자식인 RoomCard로 보냄
      isNow: false,
      isFuture: false,
      isHistory: false,
      showIdx: 4,
      showRooms: [],
    };
  },
  created() {
    if (this.title === 'Now') {
      this.isNow = true;
      this.showRooms = this.$store.state.rooms.now;
    } else if (this.title === 'Future') {
      this.isFuture = true;
      this.showRooms = this.$store.state.rooms.future;
    } else {
      this.isHistory = true;
      this.showRooms = this.$store.state.rooms.history;
    }
    if (this.isNow) this.showIdx = 3;
  },
  computed: {
    // showRooms() {
    //   if (this.isNow) return this.$store.state.rooms.now;
    //   else if (this.isFuture) return this.$store.state.rooms.future;
    //   else return this.$store.state.rooms.history;
    // },
  },
  methods: {
    showToggle() {
      this.isToggle = this.isToggle === true ? false : true;
    },
  },
};
</script>
<style>
@import './room-style.css';
</style>
