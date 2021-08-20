<template>
  <div class="mb-4 row">
    <table class="table table-striped">
      <thead>
        <tr>
          <th scope="col">Num</th>
          <th scope="col">original</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(file, index) in files" :key="index">
          <th scope="row">{{ index + 1 }}</th>
          <td>{{ file.original }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
import { showfiledetail } from '@/api/file.js';

export default {
  name: 'RoomFiledetail',
  components: {},
  data() {
    return {
      userid: this.$store.state.users.login.userid,
      roomid: this.$store.state.rooms.room.room_id,
      files: [],
      file: '',
    };
  },
  computed: {},
  methods: {},
  mounted() {
    const formData = new FormData();
    formData.append('user_id', this.userid);
    formData.append('room_id', this.roomid);
    showfiledetail(formData)
      .then(data => {
        this.files = data.data;
      })
      .catch(() => {
        console.log('error');
        this.$alertify.error('파일 목록을 가져오는 데 에러가 발생했습니다.');
      });
  },
};
</script>
