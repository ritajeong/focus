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
                placeholder="Password"
                aria-label="Password"
                aria-describedby="password-addon"
              />
            </div>
            <label>New Password</label>
            <div class="input-group mb-3">
              <input
                type="password"
                class="form-control"
                placeholder="New Password"
                aria-label="New Password"
                aria-describedby="password-addon"
              />
            </div>
            <label>New Password Confirm</label>
            <div class="input-group mb-3">
              <input
                type="password"
                class="form-control"
                placeholder="New Password Conform"
                aria-label="New Password Conform"
                aria-describedby="password-addon"
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
          <button type="button" class="btn bg-gradient-danger">Change</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ChangePasswordModal',
  components: {},
  data() {
    return {};
  },
};
</script>
