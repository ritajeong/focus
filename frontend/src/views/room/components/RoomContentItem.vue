<template>
  <section>
    <form role="form" id="contact-form" method="post" autocomplete="off">
      <div class="card-body">
        <div class="row">
          <div class="col-md-6">
            <label>Room name</label>
            <div class="input-group mb-4">
              <input
                v-model="roomName"
                class="form-control"
                type="text"
                placeholder="멋진 이름을 지어주세요."
                :readonly="isOwner"
              />
            </div>
          </div>
          <div class="col-md-6 ps-2">
            <label>Start Time</label>
            <div>
              <date-picker
                readonly
                v-model="datetime"
                type="datetime"
                format="YYYY-MM-DD hh:mm"
                :placeholder="datetime"
                :disabled-date="disabledBeforeDate"
                :disabled-time="disabledBeforeTime"
              ></date-picker>
            </div>
          </div>
        </div>
        <div class="form-group mb-4">
          <label>Room Description</label>
          <textarea
            v-model="description"
            type="text"
            class="form-control"
            rows="1"
            placeholder="방을 소개해주세요."
            :readonly="isOwner"
          ></textarea>
        </div>

        <div class="form-group mb-4">
          <label>Participant List</label>
          <div class="row">
            <div class="col-md-5">
              <input
                class="form-control"
                type="text"
                placeholder="이메일을 검색하세요."
                v-model="participantAccount"
                @keyup="inputChanged"
              />
            </div>
            <div class="col-md-5">
              <select
                name="role"
                id="role"
                class="form-select"
                aria-label="Default select example"
                v-model="roleSelected"
              >
                <option value="100-Presenter">Presenter</option>
                <option value="000-Viewer">Viewer</option>
              </select>
            </div>
            <div class="col-md-2">
              <button
                type="button"
                class="btn bg-gradient-primary"
                @click="addParticipant"
              >
                Add
              </button>
            </div>
          </div>
        </div>
        <div class="row">
          <table class="table table-striped">
            <thead>
              <tr>
                <th scope="col">Num</th>
                <th scope="col">Name</th>
                <th scope="col">Email</th>
                <th scope="col">Role</th>
                <th scope="col"></th>
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
          </table>
        </div>
      </div>
    </form>
  </section>
</template>

<script>
import Vue from 'vue';
import DatePicker from 'vue2-datepicker';
import 'vue2-datepicker/index.css';
import { findUser } from '@/api/users.js';
import VueAlertify from 'vue-alertify';
import moment from 'moment';
import { mapGetters } from 'vuex';
Vue.use(VueAlertify);

export default {
  name: 'RoomContentItem',
  components: { DatePicker },
  data() {
    return {
      user: this.$store.state.users.login,
      datetime: moment(this.$store.state.rooms.room.startTime).format(
        'YYYY-MM-DD hh:mm',
      ),
      roomName: this.$store.state.rooms.room.name,
      description: this.$store.state.rooms.room.description,
      participants: this.$store.state.rooms.room.participants,
      participant: '',
      participantAccount: '',
      roleSelected: '',
      nowDateTime: moment(new Date()).format('YYYY-MM-DD hh:mm'),
      isOwner: function () {
        if (
          this.$store.state.rooms.room.user_id == this.$store.users.login.userId
        ) {
          return true;
        }
        return false;
      },
    };
  },
  computed: {
    getParticipants() {
      return this.participants;
    },
    ...mapGetters(['users', 'room']),
  },
  methods: {
    addParticipant() {
      let msg = '';
      if (!this.participantAccount) {
        msg = '사용자를 입력해주세요';
        this.$alertify.error(msg);
        return;
      }

      console.log('참가자 이메일 검색: ' + this.participantAccount);
      console.log('selected role: ' + this.roleSelected);

      this.getUsername().then(() => {
        if (!this.participants) {
          this.$alertify.error('사용자 게정이 없습니다.');
        } else {
          this.participants.push({
            name: this.participant.data.name,
            email: this.participantAccount,
            codeId: this.roleSelected.split('-')[0],
            codeName: this.roleSelected.split('-')[1],
          });
          console.log('getUsername() success in addParticipant()');
        }
      });
    },
    deleteParticipant(email) {
      console.log('delete participant', email);

      this.participants.forEach((element, index) => {
        if (element.email == email) {
          this.participants.splice(index);
        }
      });
    },

    async getUsername() {
      try {
        this.participant = await findUser(this.participantAccount);
      } catch (err) {
        this.$alertify.error('사용자 확인이 실패했습니다..', err);
      }
    },

    disabledBeforeDate(date) {
      return (
        date <
        moment(
          `${new Date().getDate()}-${
            new Date().getMonth() + 1
          }-${new Date().getFullYear()}`,
          'DD-MM-YYYY',
        )
      );
    },
    disabledBeforeTime(time) {
      return (
        time <
        moment(
          `${new Date().getHours() - 1}:${new Date().getMinutes()}`,
          'hh:mm',
        )
      );
    },
    inputChanged() {
      this.$store.dispatch('GET_ALL_USERS', this.participantAccount);
    },
  },
};
</script>
