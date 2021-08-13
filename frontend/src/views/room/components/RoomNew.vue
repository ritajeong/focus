<template>
  <!--카드 유닛-->
  <div class="col-xl-3 col-md-6 mb-xl-0 mb-4 px-3">
    <div
      class="
        card card-background
        move-on-hover
        background-wrap
        card-new-parrent
      "
      style="
        background-image: url('../../assets/img/curved-images/curved9.jpg');
      "
    >
      <div
        class="card-body content text-white align-center justify-content-center"
      >
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
          <h4>
            <router-link to="/dashboard/create" class="text-white"
              >new</router-link
            >
          </h4>
        </div>
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
  name: 'RoomNew',
  data() {
    return {};
  },
  components: {},
};
</script>
<style>
@import './room-style.css';
.card-new-parrent {
  position: relative;
}
.card-new {
  position: absolute;
  top: 0px;
  right: 0px;
}
</style>
