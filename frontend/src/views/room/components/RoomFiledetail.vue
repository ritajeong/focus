<template>
  <div>
    <!-- <table class="table table-striped">
      <thead>
        <tr>
          <th scope="col">Num</th>
          <th scope="col">Name</th>
          <th scope="col">original</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(participant, index) in getParticipants" :key="index">
          <th scope="row">{{ index + 1 }}</th>
          <td>{{ participant.name }}</td>
          <td>{{ participant.email }}</td>
          <td>{{ participant.codeId.codeName }}</td>
          <td>
            <div v-if="index > 0 && isOwner">
              <button
                class="btn bg-gradient-danger"
                type="button"
                id="btn-delete"
                @click="deleteParticipant(participant.email)"
              >
                Delete
              </button>
            </div>
          </td>
        </tr>
      </tbody>
    </table> -->
  </div>
</template>
<script>
import { showfiledetail } from '@/api/file.js';

export default {
  name: 'RoomFiledetail',
  components: {},
  data() {
    return {
      user: this.$store.state.users.login.userid,
      room: this.$store.state.rooms.room.room_id,

      file: '',
    };
  },
  computed: {},
  methods: {},
  mounted() {
    const formData = new FormData();
    console.log(this.user);
    console.log(this.room);
    formData.append('user_id', this.user);
    formData.append('room_id', this.room);
    console.log(formData);
    showfiledetail(formData)
      .then(({ status }) => {
        console.log(status);
        this.$alertify.success('파일이 보입니다.');
      })
      .catch(() => {
        console.log('error');
        this.$alertify.error('error! catch');
      });
  },
};
</script>
