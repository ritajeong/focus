<template>
  <div>
    <template>
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
                      :value="$store.state.users.login.useremail"
                    />
                  </div>
                </div>
                <div class="mb-4">
                  <label>Name</label>
                  <div class="input-group">
                    <input
                      type="email"
                      class="form-control"
                      :value="$store.state.users.login.username"
                    />
                  </div>
                </div>
                <div class="mb-4">
                  <label>Password</label>
                  <div>
                    <div class="input-group">
                      <input
                        type="password"
                        class="form-control"
                        placeholder="현재 비밀번호를 입력하세요"
                      />
                      <!-- <button
                          type="button"
                          class="btn bg-gradient-dark w-100 text-center"
                        >
                          change
                        </button> -->
                    </div>
                  </div>
                </div>
                <div class="mb-4">
                  <label>Password Confirm</label>
                  <div class="input-group">
                    <input
                      type="password"
                      class="form-control"
                      :value="$store.state.users.login.userpwd"
                    />
                  </div>
                </div>
                <div class="form-group mb-4">
                  <label>Presentation List</label>
                  <textarea
                    name="message"
                    class="form-control"
                    id="message"
                    rows="4"
                  ></textarea>
                </div>
                <div class="row">
                  <div class="col-md-12 text-center">
                    <button
                      type="submit"
                      class="btn bg-gradient-dark w-100 text-center"
                    >
                      Modify
                    </button>
                    <br />
                    <button type="submit" class="btn btn-outline-danger w-20">
                      Withdraw
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </template>
  </div>
</template>
<script>
export default {
  name: 'MyInfo',
  components: {},
  data() {
    return {
      useremail: this.$store.state.users.login.useremail,
      username: this.$store.state.users.login.username,
      userpwd: this.$store.state.users.login.userpwd,
    };
  },
};
</script>
