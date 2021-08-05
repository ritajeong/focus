import Vue from "vue";
import VueRouter from "vue-router";
import MeetingRoom from '@/views/meetingRoom/MeetingRoom.vue'

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'MeetingRoom',
    component: MeetingRoom,
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
