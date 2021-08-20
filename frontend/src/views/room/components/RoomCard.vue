<template>
  <!--카드 유닛-->
  <div class="col-xl-3 col-lg-3 mb-xl-0 mb-4 p-3">
    <div
      class="card card-background move-on-hover background-wrap"
      v-bind:style="backgroundImg"
      style="position: relative"
      v-if="!isHistory"
    >
      <div
        class="card-body content text-white ps-0"
        data-bs-toggle="modal"
        :data-bs-target="'#RoomReadyModal' + roomInfo.room_id"
        style="cursor: pointer"
      >
        <h4 class="text-white" style="cursor: pointer; float: left">
          {{ roomInfo.name }}
        </h4>
        <p class="mb-2 text-lg" :class="{ cardPt5: isNow }">
          {{
            roomInfo.description.length > 30
              ? roomInfo.description.slice(0, 29)
              : roomInfo.description
          }}
        </p>

        <p class="mb-2 text-lg">
          시작 :
          {{
            roomInfo.startTime
              .slice(0, 16)
              .replace(/\-/g, '.')
              .replace('T', ' ')
          }}
        </p>

        <!-- 방 번호로 api요청, Room Info->아이콘으로 교체 -->
        <p class="mb-4 text-sm" v-if="isHistory">
          종료 :
          {{
            roomInfo.endTime.slice(0, 16).replace(/\-/g, '.').replace('T', ' ')
          }}
        </p>
      </div>
      <span
        class="card-info move-on-hover"
        @click.prevent="setAndGoToRoomInfo"
        type="button"
        style="z-index: 5"
      >
        <i class="fas fa-info-circle" style="z-index: 5"></i>
      </span>
    </div>
    <div
      class="card card-background background-wrap"
      v-bind:style="backgroundImg"
      style="position: relative"
      v-if="isHistory"
    >
      <div class="card-body content text-white ps-0" style="cursor: pointer">
        <h4 class="text-white" style="cursor: pointer; float: left">
          {{ roomInfo.name }}
        </h4>
        <p class="mb-2 text-lg" :class="{ cardPt5: isNow }">
          {{
            roomInfo.description.length > 30
              ? roomInfo.description.slice(0, 29)
              : roomInfo.description
          }}
        </p>

        <p class="mb-2 text-lg">
          시작 :
          {{
            roomInfo.startTime
              .slice(0, 16)
              .replace(/\-/g, '.')
              .replace('T', ' ')
          }}
        </p>

        <!-- 방 번호로 api요청, Room Info->아이콘으로 교체 -->
        <p class="mb-2 text-lg">
          종료 :
          {{
            roomInfo.endTime.slice(0, 16).replace(/\-/g, '.').replace('T', ' ')
          }}
        </p>
      </div>
      <span
        class="card-info move-on-hover"
        @click.prevent="setAndGoToRoomInfo"
        type="button"
        style="z-index: 5"
      >
        <i class="fas fa-info-circle" style="z-index: 5"></i>
      </span>
    </div>
    <!-- Modal -->
    <RoomReadyModal :roomInfo="roomInfo" :key="roomInfo.room_id" />
  </div>
  <!--카드 유닛-->
</template>
<script>
import Vue from 'vue';
import VueAlertify from 'vue-alertify';
import RoomReadyModal from './RoomReadyModal.vue';
import { mapGetters } from 'vuex';
Vue.use(VueAlertify);

export default {
  name: 'RoomCard',
  components: { RoomReadyModal },
  props: ['titleImg', 'idx'], //titleImg에 따라 backgroud변경
  data() {
    return {
      isNow: false,
      isHistory: false,
      isFuture: false,
      backgroundImg:
        "background-image: url('../../assets/img/curved-images/curved14.jpg');",
      length: 0,
    };
  },
  methods: {
    setAndGoToRoomInfo() {
      this.$store.dispatch('rooms/setRoom', this.roomInfo);
      this.$router.push('/dashboard/info');
    },
  },
  created() {
    this.isNow = this.titleImg === 'Now' ? true : false;
    this.isFuture = this.titleImg === 'Future' ? true : false;
    this.isHistory = this.titleImg === 'History' ? true : false;

    if (this.isNow) {
      this.backgroundImg =
        "background-image: url('../../assets/img/gradient/_now2.png');";
    } else if (this.isFuture) {
      this.backgroundImg =
        "background-image: url('../../assets/img/gradient/_future.png');";
    } else {
      this.backgroundImg =
        "background-image: url('../../assets/img/gradient/_history.png');";
    }
  },
  computed: {
    ...mapGetters({
      arrayNow: 'rooms/arrayNow',
      arrayFuture: 'rooms/arrayFuture',
      arrayHistory: 'rooms/arrayHistory',
    }),
    roomInfo() {
      if (this.isNow) {
        return this.arrayNow[this.idx];
      } else if (this.isFuture) {
        return this.arrayFuture[this.idx];
      } else {
        return this.arrayHistory[this.idx];
      }
    },
  },
  mounted() {},
};
</script>
<style>
@import './room-style.css';
</style>
