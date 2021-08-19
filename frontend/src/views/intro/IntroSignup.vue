<template>
  <div>
    <!--ㅎㅇ 회원가입 유효성 검사 -->
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
                <div class="pb-0 text-center">
                  <h1 class="text-gradient text-focus font-weight-bolder pb-3">
                    Sign Up
                  </h1>
                  <h4 class="mb-0 text-gradient text-focus">
                    Enter your infomation to sign up
                  </h4>
                </div>
                <div class="card-body">
                  <form role="form" @submit.prevent="submitForm()">
                    <div class="mb-3">
                      <input
                        required="required"
                        type="email"
                        class="form-control form-control-lg"
                        placeholder="Email"
                        aria-label="Email"
                        aria-describedby="email-addon"
                        v-model="useremail"
                      />
                      <!--ㅎㅇ 후순위 : validation check-->
                    </div>
                    <div class="mb-3">
                      <input
                        required="required"
                        type="text"
                        class="form-control form-control-lg"
                        placeholder="Name"
                        aria-label="Name"
                        v-model="username"
                      />
                    </div>
                    <div class="mb-3">
                      <input
                        required="required"
                        type="password"
                        class="form-control form-control-lg"
                        placeholder="Password"
                        aria-label="Password"
                        aria-describedby="password-addon"
                        v-model="userpwd"
                      />
                    </div>
                    <div class="mb-3">
                      <input
                        required="required"
                        type="password"
                        class="form-control form-control-lg"
                        placeholder="Password Confirm"
                        aria-label="Password"
                        aria-describedby="password-addon"
                        v-model="userpwdcheck"
                      />
                    </div>
                    <!--ㅎㅇ 후순위 : 약관 동의-->
                    <!-- <div class="form-check form-check-info text-left">
                      <input
                        class="form-check-input"
                        type="checkbox"
                        value=""
                        id="flexCheckDefault"
                        checked
                      />
                      <label class="form-check-label" for="flexCheckDefault">
                        I agree the
                        <a
                          href="javascript:;"
                          class="text-dark font-weight-bolder"
                          >Terms and Conditions</a
                        >
                      </label>
                    </div> -->
                    <div class="text-center pb-3">
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
                        Sign Up
                      </button>
                    </div>
                  </form>
                </div>
                <div class="card-footer text-center pt-0 px-lg-2 px-1">
                  <h3 class="mb-4 mx-auto text-lg">
                    Already have an account?
                    <router-link
                      to="login"
                      class="
                        text-dark text-gradient
                        font-weight-bold
                        text-center text-lg
                      "
                      >Sign In</router-link
                    >
                  </h3>
                </div>
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
import { registerUser, checkUser } from '@/api/users.js';
import VueAlertify from 'vue-alertify';

Vue.use(VueAlertify);

export default {
  name: 'IntroSignup',
  data() {
    return {
      user: '',
      useremail: '',
      username: '',
      userpwd: '',
      userpwdcheck: '',
    };
  },
  computed: {
    checkPwd() {
      return this.userpwd === this.userpwdcheck;
    },
  },
  methods: {
    submitForm() {
      if (!this.checkPwd) {
        this.$alertify.error('비밀번호확인이 틀렸습니다.');
        return;
      }

      this.checkEmail();
    },

    async checkEmail() {
      try {
        await checkUser(this.useremail).then(({ status }) => {
          if (status != 200) {
            this.$alertify.error('사용자 계정 확인 실패했습니다.');
          } else {
            this.registerInfo();
            this.$alertify.success('회원가입 성공했습니다.');
            this.$router.push('/', () => {});
          }
        });
      } catch (err) {
        this.$alertify.error('이미 가입된 계정입니다.');
      }
    },

    async registerInfo() {
      const userData = {
        email: this.useremail,
        name: this.username,
        password: this.userpwd,
      };
      const { data } = await registerUser(userData);
      console.log(data);
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
