<template>
  <div>
    <section>
      <div class="container py-4 mb-3">
        <div class="row">
          <div
            class="
              col-xl-4 col-lg-6
              mx-auto
              d-flex
              justify-content-center
              flex-column
              px-6
              py-7
            "
          >
            <h3 class="text-center">My Page</h3>
            <div class="card-body">
              <div class="row">
                <div class="col-md-12 pt-5">
                  <label><h6>Email</h6></label>
                  <div class="input-group">
                    <input
                      type="email"
                      class="form-control"
                      :value="useremail"
                      readonly
                    />
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <label><h6>Name</h6></label>
                  <div class="input-group row">
                    <div class="col-9">
                      <input
                        type="name"
                        class="form-control"
                        v-model="username"
                      />
                    </div>
                    <div class="col-3">
                      <button
                        type="button"
                        class="btn bg-gradient-dark"
                        @click="modifyName"
                      >
                        Modify
                      </button>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12 text-center mt-5">
                  <button
                    type="button"
                    class="btn bg-gradient-dark w-50"
                    data-bs-toggle="modal"
                    data-bs-target="#ModalChangePassword"
                  >
                    Change Password
                  </button>
                  <br />
                  <button
                    type="button"
                    class="text-danger btn btn-outline-danger w-50"
                    data-bs-toggle="modal"
                    data-bs-target="#modal-notification"
                  >
                    Withdraw
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <ChangePasswordModal></ChangePasswordModal>
    <WithdrawModal></WithdrawModal>
  </div>
</template>
<script>
import Vue from 'vue';
import ChangePasswordModal from './components/ChangePasswordModal.vue';
import WithdrawModal from './components/WithdrawModal.vue';
import { updateUserName } from '@/api/users.js';
import VueAlertify from 'vue-alertify';
Vue.use(VueAlertify);

export default {
  name: 'MyInfo',
  components: { ChangePasswordModal, WithdrawModal },
  data() {
    return {
      useremail: this.$store.state.users.login.useremail,
      username: this.$store.state.users.login.username,
    };
  },
  methods: {
    modifyName() {
      let message = '';
      if (!this.username) {
        message = '변경할 이름을 입력하세요';
        this.$alertify.error(message);
        return;
      }
      let userData = {
        user_id: this.$store.state.users.login.userid,
        name: this.username,
      };
      updateUserName(userData)
        .then(({ status }) => {
          if (status != 200) {
            this.$alertify.error('이름 변경중 오류가 발생했습니다.');
            return;
          } else {
            this.$alertify.success('이름이 변경됐습니다.');
            this.$store.dispatch('users/SET_NAME', this.username);
          }
        })
        .catch(() => {
          this.$alertify.error('이름 변경 시도가 실패했습니다.');
        });
    },
  },
};
</script>
