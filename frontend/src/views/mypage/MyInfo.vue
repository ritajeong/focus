<template>
  <div>
    <section>
      <div class="container py-4">
        <div class="row">
          <div
            class="col-lg-6 mx-auto d-flex justify-content-center flex-column"
          >
            <h3 class="text-center">My Page</h3>

            <div class="card-body">
              <div class="mb-4">
                <label>Email</label>
                <div class="input-group">
                  <input
                    type="email"
                    class="form-control"
                    :value="useremail"
                    readonly
                  />
                </div>
              </div>
              <div class="mb-9">
                <label>Name</label>
                <div class="input-group row">
                  <div class="col-9">
                    <input
                      type="name"
                      class="form-control"
                      v-model="username"
                    />
                  </div>

                  <button
                    type="button"
                    class="btn bg-gradient-dark col-3"
                    @click="modifyName"
                  >
                    Modify
                  </button>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <button
                    type="button"
                    class="btn bg-gradient-primary w-100"
                    data-bs-toggle="modal"
                    data-bs-target="#ModalChangePassword"
                  >
                    Change Password
                  </button>
                  <br />
                  <button
                    type="button"
                    class="btn bg-gradient-danger w-100"
                    data-bs-toggle="modal"
                    data-bs-target="#modal-notification"
                  >
                    Withdrow
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
import ChangePasswordModal from './components/ChangePasswordModal.vue';
import WithdrawModal from './components/WithdrawModal.vue';
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
    modifyName() {},
  },
};
</script>
