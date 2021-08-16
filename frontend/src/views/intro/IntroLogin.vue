<template>
  <div>
    <section>
      <div class="page-header min-vh-100">
        <div class="container">
          <div class="row">
            <div
              class="
                col-xl-4 col-lg-5 col-md-7
                d-flex
                flex-column
                mx-lg-0 mx-auto
              "
            >
              <div class="card card-plain">
                <div class="card-header pb-0 text-left">
                  <h4 class="font-weight-bolder">Sign In</h4>
                  <p class="mb-0">Enter your email and password to sign in</p>
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
                          w-100
                          mt-4
                          mb-0
                        "
                      >
                        Sign in
                      </button>
                    </div>
                  </form>
                  <div class="card-footer text-left pt-0 px-lg-2 px-1">
                    <p class="mb-2 text-sm mx-auto">
                      Don't have an account?
                      <router-link
                        v:bind:disabled="!isUseremailValid || password"
                        to="signup"
                        class="text-dark text-gradient font-weight-bold"
                        >Sign Up</router-link
                      >
                    </p>
                    <p class="mb-2 text-sm mx-auto">
                      Did you forget password?
                      <router-link
                        to=""
                        class="text-dark text-gradient font-weight-bold"
                        data-bs-toggle="modal"
                        data-bs-target="#ModalChangePasswordByEmail"
                        >Change password</router-link
                      >
                    </p>
                  </div>
                </div>
                <div class="card-footer text-left pt-0 px-lg-2 px-1"></div>
              </div>
            </div>
            <div
              class="
                col-6
                d-lg-flex d-none
                h-100
                my-auto
                pe-0
                position-absolute
                top-0
                end-0
                text-center
                justify-content-center
                flex-column
              "
            >
              <div
                class="
                  position-relative
                  bg-gradient-dark
                  h-100
                  m-3
                  px-7
                  border-radius-lg
                  d-flex
                  flex-column
                  justify-content-center
                "
              >
                <img
                  src="@/assets/img/shapes/pattern-lines.svg"
                  alt="pattern-lines"
                  class="position-absolute opacity-4 start-0"
                />
                <div class="position-relative">
                  <img
                    class="max-width-500 w-100 position-relative z-index-2"
                    src="@/assets/img/illustrations/sign-up.png"
                  />
                </div>
                <h4 class="mt-5 text-white font-weight-bolder">
                  "Attention is the new currency"
                </h4>
                <p class="text-white">
                  The more effortless the writing looks, the more effort the
                  writer actually put into the process.
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <ChangePwdByEmailModal></ChangePwdByEmailModal>
    </section>
  </div>
</template>
<script>
import Vue from 'vue';
import { loginUser } from '@/api/users.js';
import VueAlertify from 'vue-alertify';
import ChangePwdByEmailModal from './components/ChangePwdByEmailModal.vue';
Vue.use(VueAlertify);

export default {
  name: 'IntroLogin',
  components: { ChangePwdByEmailModal },
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
        console.log('submitForm()');
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

          this.$router.push('/dashboard');
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
