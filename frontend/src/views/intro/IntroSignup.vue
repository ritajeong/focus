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
                  <h4 class="font-weight-bolder">Sign Up</h4>
                  <p class="mb-0">Enter your infomation to sign up</p>
                </div>
                <div class="card-body">
                  <form role="form">
                    <div class="mb-3">
                      <input
                        type="email"
                        class="form-control form-control-lg"
                        placeholder="Email"
                        aria-label="Email"
                        aria-describedby="email-addon"
                        v-model="userEmail"
                      />
                    </div>
                    <div class="mb-3">
                      <input
                        type="text"
                        class="form-control form-control-lg"
                        placeholder="Name"
                        aria-label="Name"
                        v-model="userName"
                      />
                    </div>
                    <div class="mb-3">
                      <input
                        type="password"
                        class="form-control form-control-lg"
                        placeholder="Password"
                        aria-label="Password"
                        aria-describedby="password-addon"
                        v-model="userPwd"
                      />
                    </div>
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
                    <div class="text-center">
                      <button
                        type="button"
                        class="
                          btn btn-lg
                          bg-gradient-dark
                          btn-lg
                          w-100
                          mt-4
                          mb-0
                        "
                        @click.prevent="userSignup()"
                      >
                        Sign Up
                      </button>
                    </div>
                  </form>
                </div>
                <div class="card-footer text-center pt-0 px-lg-2 px-1">
                  <p class="mb-4 text-sm mx-auto">
                    Already have an account?
                    <router-link
                      to="login"
                      class="text-dark text-gradient font-weight-bold"
                      >Sign In</router-link
                    >
                  </p>
                </div>
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
                    src="@/assets/img/illustrations/rocket-white.png"
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
    </section>
  </div>
</template>
<script>
import Vue from 'vue';
import axios from '@/api/axios.js';
import VueAlertify from 'vue-alertify';

Vue.use(VueAlertify);

export default {
  name: 'IntroSignup',
  data() {
    return {
      userName: '',
      userPwd: '',
      userPwdConfirm: '',
      userEmail: '',
    };
  },
  created() {},
  methods: {
    userSignup() {
      axios
        .post('/users', {
          email: this.userEmail,
          name: this.userName,
          password: this.userPwd,
        })
        .then(({ data }) => {
          console.log(data);
          this.$alertify.alert(
            '회원 가입 성공',
            '회원 가입이 완료되었습니다.\n메인 페이지로 이동합니다.',
          );
          this.$router.push('/');
        })
        .catch(error => {
          this.$alertify.alert(
            '회원 가입 오류',
            '회원 가입에 오류가 생겼습니다.',
          );
          console.log('userSignup: error ');
          console.log(error);
        });
    },
  },
};
/*
{
  "email": "string",
  "name": "string",
  "password": "string",
  "userId": 0
}
*/
</script>
