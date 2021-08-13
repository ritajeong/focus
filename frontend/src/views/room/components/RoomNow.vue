<template>
  <div>
    <!--카드 유닛-->
    <div
      class="col-xl-3 col-md-6 mb-xl-0 mb-4 px-3"
      data-bs-toggle="modal"
      data-bs-target="#exampleModalMessage"
    >
      <div
        class="card card-background move-on-hover background-wrap"
        style="
          background-image: url('../../assets/img/curved-images/curved10.jpg');
        "
      >
        <div class="card-body content text-white">
          <h4 class="text-white">Room Name</h4>
          <p class="mb-2 text-sm">Room Id</p>
          <p class="mb-4 text-sm">Room Description</p>
          <router-link to="" class="content text-white">
            JOIN ROOM
          </router-link>
          <router-link to="/dashboard/info" class="content text-white">
            ROOM INFO
          </router-link>
        </div>
      </div>
    </div>
    <!--카드 유닛-->
    <!-- Modal -->
    <div
      class="modal fade"
      id="exampleModalMessage"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalMessageTitle"
      aria-hidden="true"
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
                  >Room Name:
                </label>

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
                <label for="message-text" class="col-form-label"
                  >My Name:</label
                >
                <input
                  type="text"
                  class="form-control"
                  value="내 이름"
                  id="room-description"
                  readonly
                />
              </div>
            </form>
            <video width="100%" id="video" autoplay="true"></video>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn bg-gradient-secondary"
              data-bs-dismiss="modal"
            >
              Close
            </button>
            <button type="button" class="btn bg-gradient-primary">
              Send message
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Vue from 'vue';
import VueAlertify from 'vue-alertify';

Vue.use(VueAlertify);

export default {
  name: 'RoomNow',
  data() {
    return {};
  },
  components: {},
};
</script>
<style>
@import './room-style.css';
</style>
