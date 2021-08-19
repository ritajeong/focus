<template>
  <!--카드 유닛-->
  <div class="col-xl-3 col-lg-3 mb-xl-0 mb-4 p-3">
    <div
      class="
        card card-background
        move-on-hover
        background-wrap
        card-new-parrent
      "
      style="background-image: url('../../assets/img/gradient/_new.png')"
    >
      <router-link to="/dashboard/create" class="text-white">
        <div
          class="
            card-body
            content
            text-white
            align-center
            text-center
            justify-content-center
            card-new
          "
        >
          <h2 class="text-white"><i class="fas fa-plus"></i></h2>
          <h4 class="text-white">new</h4>
        </div></router-link
      >
    </div>
  </div>
  <!--카드 유닛-->
</template>
<script>
import Vue from 'vue';
import VueAlertify from 'vue-alertify';
Vue.use(VueAlertify);

export default {
  name: 'RoomNew',
  data() {
    return {};
  },
  components: {},
};
</script>
<style>
@import './room-style.css';
</style>
