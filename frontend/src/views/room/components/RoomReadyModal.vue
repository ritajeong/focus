<template>
  <!-- Modal -->
  <div
    class="modal fade"
    id="exampleModalMessage"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalMessageTitle"
  >
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">FOCUS</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          >
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">
          <form>
            <div class="form-group">
              <label for="recipient-name" class="col-form-label"
                >Room Name:</label
              >
              <input
                type="text"
                class="form-control"
                value="방 이름"
                id="room-name"
                readonly
              />
            </div>
            <div class="form-group">
              <label for="message-text" class="col-form-label"
                >Room Description:</label
              >

              <input
                type="text"
                class="form-control"
                value="방 설명"
                id="room-description"
                readonly
              />
            </div>
            <div class="form-group">
              <label for="message-text" class="col-form-label">My Name:</label>
              <input
                type="text"
                class="form-control"
                value="내 이름"
                id="room-description"
                readonly
              />
            </div>
          </form>
          <video width="500" height="500" id="video" autoplay="true"></video>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn bg-gradient-secondary"
            data-bs-dismiss="modal"
          >
            Close
          </button>
          <button type="button" class="btn bg-gradient-primary">Join</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RoomReadyModal',
  components: {},
  props: {},
  data() {
    return {};
  },
  computed: {},
  mounted() {},
  methods: {},
};
</script>
