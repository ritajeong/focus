<template>
  <!--now-->
  <div class="row mx-0">
    <div class="col-12 p-0">
      <div class="card m-5">
        <div class="card-header pb-0 ps-5">
          <!--props값에 따라 title 출력 [Now, Future, History]-->
          <div class="row">
            <div class="col-11">
              <h3 class="mb-1 ps-3">
                {{ title }}
              </h3>
            </div>
            <div
              class="col-1 icon-up"
              style="position: absolute"
              v-if="lengthRooms > 0"
            >
              <h2>
                <i
                  class="fas fa-chevron-down move-on-hover ps-3"
                  v-show="isToggle"
                  @click="showToggle"
                ></i
                ><i
                  class="fas fa-chevron-up move-on-hover ps-3"
                  @click="showToggle"
                  v-show="!isToggle"
                ></i>
              </h2>
            </div>
          </div>
        </div>
        <!--카드목록-->
        <div class="card-body ps-5 pe-10">
          <div class="row" v-show="isToggle">
            <RoomNew v-show="isNow" />
            <RoomCard
              :titleImg="titleImg"
              :idx="idx"
              v-for="(room, idx) in showRooms"
              :key="idx"
              v-show="idx <= showIdx"
            />
            <h4 class="ps-4" v-if="lengthRooms === 0">No Rooms in here.</h4>
          </div>
          <!-- more-->
          <div
            class="card-last move-on-hover text-center"
            v-show="lengthRooms > 0 && isToggle"
            @click.stop="moreCard"
          >
            <a href="#"
              ><h2><i class="fas fa-plus"></i></h2>
              <h4>more</h4>
            </a>
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
    // console.log(this.isNow + ' ' + this.showRooms);
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
