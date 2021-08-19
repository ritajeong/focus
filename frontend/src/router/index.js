import Vue from 'vue';
import VueRouter from 'vue-router';
import VueAlertify from 'vue-alertify';

import IntroPage from '@/views/intro/IntroPage.vue';
import IntroContent from '@/views/intro/IntroContent.vue';
import IntroLogin from '@/views/intro/IntroLogin.vue';
import IntroSignup from '@/views/intro/IntroSignup.vue';

import RoomPage from '@/views/room/RoomPage';
import RoomDashboard from '@/views/room/RoomDashboard';
import RoomCreate from '@/views/room/RoomCreate';
import RoomInfo from '@/views/room/RoomInfo';
import RoomTutorial from '@/views/room/RoomTutorial';

import MeetingRoom from '@/views/meetingRoom/MeetingRoom.vue';

import MyPage from '@/views/mypage/MyPage.vue';
import MyInfo from '@/views/mypage/MyInfo.vue';

import store from '@/store/index.js';

Vue.use(VueRouter);
Vue.use(VueAlertify);
const router = new VueRouter({
  // mode: 'history',
  routes: [
    {
      path: '/',
      component: IntroPage,
      meta: { authRequired: false },
      children: [
        {
          path: '',
          component: IntroContent,
        },
        {
          path: '/login',
          component: IntroLogin,
        },
        {
          path: '/signup',
          component: IntroSignup,
        },
      ],
    },
    {
      path: '/dashboard',
      component: RoomPage,
      meta: { authRequired: true },
      children: [
        {
          path: '',
          component: RoomDashboard,
        },
        {
          path: '/dashboard/create',
          component: RoomCreate,
        },
        {
          path: '/dashboard/info',
          component: RoomInfo,
        },
        {
          path: '/dashboard/tutorial',
          component: RoomTutorial,
        },
      ],
    },
    {
      path: '/meetingroom',
      name: 'MeetingRoom',
      component: MeetingRoom,
      meta: { authRequired: true },
    },
    {
      path: '/mypage',
      component: MyPage,
      meta: { authRequired: true },
      children: [
        {
          path: '',
          component: MyInfo,
        },
      ],
    },
  ],
  // scrollBehavior(to, from, savedPosition) {
  //   return { x: 0, y: 0 };
  // },
});

// let isLogin =
router.beforeEach(function (to, from, next) {
  var authRequired = to.matched.some(routeInfo => {
    // console.log(routeInfo);
    return routeInfo.meta.authRequired;
  });
  if (!authRequired || (authRequired && store.state.users.login.isLogin)) {
    // console.log('authRequired : ' + authRequired);
    // console.log('isLogin : ' + store.state.users.login.isLogin);
    next();
  } else {
    // VueAlertify.error('로그인이 필요합니다');
    // this.$alertify.error('로그인이 필요합니다');
    alert('로그인이 필요합니다');
    router.push('/login');
    // console.log('authRequired : ' + authRequired);
    // console.log('isLogin : ' + store.state.users.login.isLogin);
  }
});

export default router;
