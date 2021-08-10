<template>
  <div>
    <!--ㅎㅇ 회원가입 유효성 검사 -->
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
                        required="required"
                        type="email"
                        class="form-control form-control-lg"
                        placeholder="Email"
                        aria-label="Email"
                        aria-describedby="email-addon"
                        pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
                        v-model="useremail"
                      />

                      <!--ㅎㅇ 후순위 : 아이디 중복확인 추가(/users/{email})-->
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
                        @click.prevent="submitForm()"
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
import { registerUser, checkUser } from '@/api/users.js';
import VueAlertify from 'vue-alertify';

Vue.use(VueAlertify);

export default {
  name: 'IntroSignup',
  data() {
    return {
      username: '',
      userpwd: '',
      userpwdcheck: '',
      useremail: '',
      isPwdSame: 'false',
    };
  },
  created: {
    checkPwd() {
      return (this.isPwdSame =
        this.userpwd === this.userpwdcheck ? true : false);
    },
  },
  methods: {
    async submitForm() {
      console.log('submitForm()');
      if (this.checkEmail() !== 409) {
        //ㅇㅇ api오류 . 아이디 중복확인과 멤버 초대가 상충함 -> 해결하고 validation check 작업하기
        this.$alertify.error('이미 가입된 이메일입니다.');
        return;
      }

      if (!this.checkPwd) {
        this.$alertify.error('비밀번호확인이 틀렸습니다.');
        return;
      }

      const userData = {
        name: this.username,
        email: this.useremail,
      };
      // const response = registerUser(userData);
      const { data } = await registerUser(userData);
      console.log(data.name);
      this.initForm();
    },
    async checkEmail() {
      console.log('checkEmail()');
      const { data } = await checkUser(this.useremail);
      console.log(data);
      return data.statusCode;
    },
    initForm() {
      this.username = '';
      this.userpwd = '';
      this.useremail = '';
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
