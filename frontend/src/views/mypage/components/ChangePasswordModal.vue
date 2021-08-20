<template>
  <!-- Modal -->
  <div
    class="modal fade"
    id="ModalChangePassword"
    tabindex="-1"
    role="dialog"
    aria-labelledby="ModalChangePassword"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h6 class="modal-title" id="ModalWithdraw">Change Password</h6>
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
          <form role="form text-left">
            <label>Password</label>
            <div class="input-group mb-3">
              <input
                type="password"
                class="form-control"
                :class="{
                  'is-valid': isCurrentPwdValid,
                  'is-invalid': isCurrentPwdValid === false,
                }"
                placeholder="Password"
                aria-label="Password"
                aria-describedby="password-addon"
                v-model="currentPassword"
              />
            </div>
            <label>New Password</label>
            <div class="input-group mb-3">
              <input
                type="password"
                class="form-control"
                :class="{
                  'is-valid': isNewPwdValid,
                  'is-invalid': isNewPwdValid === false,
                }"
                placeholder="New Password"
                aria-label="New Password"
                aria-describedby="password-addon"
                v-model="newPassword"
              />
            </div>
            <label>New Password Confirm</label>
            <div class="input-group mb-3">
              <input
                type="password"
                class="form-control"
                :class="{
                  'is-valid': isNewPwdConfirmValid,
                  'is-invalid': isNewPwdConfirmValid === false,
                }"
                placeholder="New Password Conform"
                aria-label="New Password Conform"
                aria-describedby="password-addon"
                v-model="newPasswordConfirm"
              />
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn bg-gradient-secondary"
            data-bs-dismiss="modal"
          >
            Cancel
          </button>
          <button
            type="button"
            class="btn bg-gradient-danger"
            @click="changePassword()"
            data-bs-dismiss="modal"
          >
            Change
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from 'vue';
import VueAlertify from 'vue-alertify';
import { updateUserPwd } from '@/api/users.js';
Vue.use(VueAlertify);
export default {
  name: 'ChangePasswordModal',
  components: {},
  data() {
    return {
      currentPassword: '',
      newPassword: '',
      newPasswordConfirm: '',
      isCurrentPwdValid: '',
      isNewPwdValid: '',
      isNewPwdConfirmValid: '',
    };
  },
  methods: {
    changePassword() {
      let message = '';
      let error = false;
      if (!this.currentPassword) {
        message = '현재 비밀번호를 입력해주세요.';
        this.isCurrentPwdValid = false;
        error = true;
      } else if (!this.newPassword) {
        message = '변경할 비밀번호를 입력해주세요.';
        this.isNewPwdValid = false;
        error = true;
      } else if (!this.newPasswordConfirm) {
        message = '변경할 비밀번호를 입력해주세요.';
        this.isNewPwdConfirmValid = false;
        error = true;
      } else if (this.newPassword != this.newPasswordConfirm) {
        message = '새로운 비밀번호가 일치하지 않습니다.';
        this.isNewPwdValid = false;
        this.isNewPwdConfirmValid = false;
        error = true;
      } else if (this.newPassword == this.newPasswordConfirm) {
        this.isNewPwdValid = true;
        this.isNewPwdConfirmValid = true;
      }

      if (error) {
        this.$alertify.error(message);
        return;
      } else {
        let userData = {
          user_id: this.$store.state.users.login.userid,
          currentPassword: this.currentPassword,
          newPassword: this.newPassword,
        };
        updateUserPwd(userData)
          .then(({ status }) => {
            if (status == 401) {
              this.$alertify.error('현재 비밀번호가 틀립니다.');
              this.isCurrentPwdValid = false;
            } else if (status != 200) {
              this.$alertify.error('비밀번호 변경 시도중 실패했습니다.');
              this.isCurrentPwdValid = true;
              this.isCurrentPwdValid = false;
              this.isNewPwdValid = false;
              this.isNewPwdConfirmValid = false;
            } else {
              this.isCurrentPwdValid = true;
              this.$alertify.success('비밀번호가 변경됐습니다.');
            }
          })
          .catch(() => {
            this.$alertify.error('비밀번호 변경이 실패했습니다.');
            this.isCurrentPwdValid = true;
            this.isCurrentPwdValid = false;
            this.isNewPwdValid = false;
            this.isNewPwdConfirmValid = false;
          });
      }
    },
  },
};
</script>
