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
              <p class="text-center">내 정보 조회 및 수정, 탈퇴</p>
              <form
                role="form"
                id="contact-form"
                method="post"
                autocomplete="off"
              >
                <div class="card-body">
                  <div class="mb-4">
                    <label>Email</label>
                    <div class="input-group">
                      <input
                        type="email"
                        class="form-control"
                        value="test@test.com"
                      />
                    </div>
                  </div>
                  <div class="mb-4">
                    <label>Name</label>
                    <div class="input-group">
                      <input type="email" class="form-control" value="test" />
                    </div>
                  </div>
                  <div class="mb-4">
                    <label>Password</label>
                    <div class="input-group">
                      <input
                        type="password"
                        class="form-control"
                        value="test"
                      />
                    </div>
                  </div>
                  <div class="mb-4">
                    <label>Password Confirm</label>
                    <div class="input-group">
                      <input
                        type="password"
                        class="form-control"
                        value="test"
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
                    <div class="col-md-12">
                      <button type="submit" class="btn bg-gradient-dark w-100">
                        Modify
                      </button>
                      <button type="submit" class="btn bg-gradient-dark w-100">
                        Withdrawal
                      </button>
                    </div>
                  </div>
                </div>
              </form>
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
};
</script>
