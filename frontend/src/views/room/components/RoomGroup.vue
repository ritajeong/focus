<template>
  <!--now-->
  <div class="row mx-0">
    <div class="col-12">
      <div class="card m-5">
        <div class="card-header pb-0 ps-5">
          <!--props값에 따라 title 출력 [Now, Future, History]-->
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
        <!--카드목록-->
        <div class="card-body ps-5 pe-10" v-show="isToggle">
          <div class="row">
            <RoomNew v-show="isNow" />
            <RoomCard
              :titleImg="titleImg"
              :idx="idx"
              v-for="(room, idx) in showRooms"
              :key="idx"
              v-show="idx <= showIdx"
              :class="{ cardLastParrnet: isLast }"
            />
            <h2 v-if="lengthRooms === 0">방이 없습니다.</h2>
          </div>
          <!-- more-->
          <div class="card-last text-center" @click.stop="moreCard">
            <a href="#"
              ><h2><i class="fas fa-plus"></i></h2>
              <h4>more</h4></a
            >
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
import RoomNew from '@/views/room/components/RoomNew';
import { mapGetters } from 'vuex';
Vue.use(VueAlertify);

export default {
  name: 'RoomGroup',
  props: ['title'],
  components: {
    RoomCard,
    RoomNew,
  },
  data() {
    return {
      isToggle: true,
      titleImg: this.title, //부모(RoomDashboard)부터 받은 title을 다시 자식인 RoomCard로 보냄
      isNow: false,
      isFuture: false,
      isHistory: false,
      showIdx: 3,
    };
  },
  created() {
    if (this.title === 'Now') {
      this.isNow = true;
    } else if (this.title === 'Future') {
      this.isFuture = true;
    } else {
      this.isHistory = true;
    }
    if (this.isNow) this.showIdx = 2;
  },
  computed: {
    isLengthZero() {
      if (this.length === 0) return true;
      else return false;
    },
    ...mapGetters({
      arrayNow: 'rooms/arrayNow',
      arrayFuture: 'rooms/arrayFuture',
      arrayHistory: 'rooms/arrayHistory',
      lengthNow: 'rooms/lengthNow',
      lengthFuture: 'rooms/lengthFuture',
      lengthHistory: 'rooms/lengthHistory',
    }),
    showRooms() {
      if (this.isNow) {
        return this.arrayNow;
      } else if (this.isFuture) {
        return this.arrayFuture;
      } else {
        return this.arrayHistory;
      }
    },
    lengthRooms() {
      if (this.isNow) {
        return this.lengthNow;
      } else if (this.isFuture) {
        return this.lengthFuture;
      } else {
        return this.lengthHistory;
      }
    },
  },
  mounted() {
    console.log(this.isNow + ' ' + this.showRooms);
  },
  methods: {
    showToggle() {
      this.isToggle = this.isToggle === true ? false : true;
    },
    moreCard() {
      this.showIdx += 4;
    },
  },
};
</script>
<style>
@import './room-style.css';
</style>
