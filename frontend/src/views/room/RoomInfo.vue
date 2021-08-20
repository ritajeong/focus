<template>
  <div>
    <section>
      <div class="container py-4">
        <div class="row">
          <div
            class="
              col-xl-7 col-lg-10
              mx-auto
              d-flex
              justify-content-center
              flex-column
              px-10
            "
          >
            <h3 class="text-center">Room Info</h3>
            <RoomContent></RoomContent>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import RoomContent from './components/RoomContent.vue';
export default {
  name: 'RoomInfo',
  components: { RoomContent },
  data() {
    return {};
  },
};
</script>
