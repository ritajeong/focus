<template>
  <div>
    <section>
      <div class="page-header min-vh-100">
        <div class="oblique position-absolute top-0 h-100 d-md-block d-none">
          <div
            class="
              oblique-image
              bg-cover
              position-absolute
              fixed-top
              ms-auto
              h-100
              z-index-0
              ms-n9
            "
            style="background-image: url(../../assets/img/gradient/Sky.jpg)"
          ></div>
        </div>
        <div class="container">
          <div class="row">
            <div
              class="
                col-xl-4 col-lg-5 col-md-7
                d-flex
                flex-column
                mx-lg-8 mx-auto
              "
            >
              <div class="card card-plain">
                <div class="pb-0 text-center pb-3">
                  <h1 class="text-gradient text-focus font-weight-bolder pb-3">
                    Sign In
                  </h1>
                  <h5 class="mb-0 text-gradient text-focus">
                    Enter your email and password to sign in
                  </h5>
                </div>
                <div class="card-body">
                  <form role="form" class="mb-4" @submit.prevent="submitForm()">
                    <div class="mb-3">
                      <input
                        type="email"
                        class="form-control form-control-lg"
                        placeholder="Email"
                        aria-label="Email"
                        v-model="useremail"
                      />
                    </div>
                    <div class="mb-3">
                      <input
                        type="password"
                        class="form-control form-control-lg"
                        placeholder="Password"
                        aria-label="Password"
                        v-model="userpwd"
                      />
                    </div>
                    <!-- ㅎㅇ 아이디 기억-->
                    <!-- <div class="form-check form-switch">
                      <input
                        class="form-check-input"
                        type="checkbox"
                        id="rememberMe"
                      />
                      <label class="form-check-label" for="rememberMe"
                        >Remember me</label
                      >
                    </div> -->
                    <div class="text-center">
                      <button
                        type="submit"
                        class="
                          btn btn-lg
                          bg-gradient-dark
                          btn-lg
                          w-50
                          mt-4
                          mb-0
                          text-lg
                        "
                      >
                        Sign in
                      </button>
                    </div>
                  </form>
                  <div class="card-footer text-center pt-0 px-lg-2 px-1">
                    <h3 class="mb-2 text-lg mx-auto">
                      Don't have an account?
                      <router-link
                        v:bind:disabled="!isUseremailValid || password"
                        to="signup"
                        class="text-dark text-gradient font-weight-bold"
                        >Sign Up</router-link
                      >
                    </h3>
                    <!-- <p class="mb-2 text-sm mx-auto">
                      Did you forget password?
                      <router-link
                        to=""
                        class="text-dark text-gradient font-weight-bold"
                        data-bs-toggle="modal"
                        data-bs-target="#ModalChangePasswordByEmail"
                        >Change password</router-link
                      >
                    </p> -->
                  </div>
                </div>
                <div class="card-footer text-left pt-0 px-lg-2 px-1"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import Vue from 'vue';
import { loginUser } from '@/api/users.js';
import VueAlertify from 'vue-alertify';
Vue.use(VueAlertify);

export default {
  name: 'IntroLogin',
  components: {},
  data() {
    return {
      useremail: '',
      userpwd: '',
      logMessage: '',
    };
  },
  computed: {},
  methods: {
    async submitForm() {
      try {
        const userData = {
          //보낼때
          email: this.useremail,
          password: this.userpwd,
        };
        await loginUser(userData).then(({ data }) => {
          const userInfo = {
            //받을때
            id: data.userId,
            email: data.email,
            name: data.name,
            isLogin: true,
          };
          this.$store.commit('users/SET_LOGIN', userInfo);
          // this.$router.push('/dashboard');
        });
      } catch (error) {
        console.log(error);
        this.$alertify.error('이메일 또는 비밀번호를 확인하세요.');
        this.initForm();
      }
    },
    initForm() {
      this.useremail = '';
      this.userpwd = '';
    },
  },
};
</script>
<style scoped>
.text-gradient {
  background-clip: text;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  position: relative;
  z-index: 1;
}
.text-gradient.text-focus {
  background-image: linear-gradient(310deg, #141727, #3f92be);
}
</style>
