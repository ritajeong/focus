<template>
  <div>
    <div>room: <input type="text" v-model="room" /></div>
    <div>name: <input type="text" v-model="name" /></div>
    <div>userId: <input type="text" v-model="userId" /></div>
    <div><button @click="register">submit</button></div>
  </div>
</template>

<script>
// import "./template.scss";

export default {
  name: 'MeetingRoomEnter',
  components: {},
  // : props
  props: {},
  // : data
  data() {
    return {
      room: null,
      name: null,
      userId: null,
    };
  },
  // : computed
  computed: {},
  // : lifecycle hook
  mounted() {
    const url = 'wss://' + location.host + '/groupcall';
    this.$store.dispatch('meetingRoom/wsInit', url);
  },
  // : methods
  methods: {
    register: function () {
      const myName = this.name + '-' + this.userId;
      const message = {
        id: 'joinRoom',
        name: myName,
        room: this.room,
      };
      this.$store.dispatch('meetingRoom/sendMessage', message);
      this.$store.dispatch('meetingRoom/setMyName', myName);
    },
  },
};
</script>
