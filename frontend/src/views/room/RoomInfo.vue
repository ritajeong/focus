<template>
  <div>
    <section>
      <div class="container py-4">
        <div class="row">
          <div
            class="col-lg-7 mx-auto d-flex justify-content-center flex-column"
          >
            <h3 class="text-center">Room Info</h3>
            <RoomContentItem></RoomContentItem>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import RoomContentItem from './components/RoomContentItem.vue';
export default {
  name: 'RoomInfo',
  components: { RoomContentItem },
  data() {
    return {};
  },
};
</script>
