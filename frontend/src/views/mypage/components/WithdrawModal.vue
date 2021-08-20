<template>
  <div
    class="modal fade"
    id="modal-notification"
    tabindex="-1"
    role="dialog"
    aria-labelledby="modal-notification"
    aria-hidden="true"
  >
    <div
      class="modal-dialog modal-danger modal-dialog-centered modal-"
      role="document"
    >
      <div class="modal-content">
        <div class="modal-header">
          <h6 class="modal-title" id="modal-notification">
            Your attention is required
          </h6>
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
          <div class="py-3 text-center">
            <i class="ni ni-bell-55 ni-3x"></i>
            <h4 class="text-gradient text-danger mt-4">
              Are you sure withdraw our site?
            </h4>
          </div>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn bg-gradient-secondary"
            data-bs-dismiss="modal"
          >
            No
          </button>
          <button
            type="button"
            class="btn bg-gradient-danger"
            @click="withdraw"
            data-bs-dismiss="modal"
          >
            Yes
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from 'vue';
import VueAlertify from 'vue-alertify';
import { deleteUser } from '@/api/users.js';
Vue.use(VueAlertify);
export default {
  name: 'WithdrawModal',
  components: {},
  props: {},
  data() {
    return {
      // userId: '',
    };
  },
  methods: {
    withdraw() {
      const userId = this.$store.state.users.login.userid;
      deleteUser(userId).then(({ status }) => {
        if (status != 200) {
          this.$alertify.error('탈퇴에 실패했습니다.');
        } else {
          this.$alertify.success('탈퇴 성공했습니다.');
          this.$router.push('/dashboard');
        }
      });
    },
  },
};
</script>
