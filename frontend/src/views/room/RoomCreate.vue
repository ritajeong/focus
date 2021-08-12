<template>
  <section>
    <div class="container py-4">
      <div class="row">
        <div class="col-lg-7 mx-auto d-flex justify-content-center flex-column">
          <h3 class="text-center">Create new room</h3>
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
                    />
                  </div>
                </div>
                <div class="col-md-6 ps-2">
                  <label>Start Time</label>
                  <div>
                    <date-picker
                      v-model="datetime"
                      type="datetime"
                      format="YYYY-MM-DD hh:mm A"
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
                ></textarea>
              </div>

              <div class="form-group mb-4">
                <label>Participant List</label>
                <div class="row">
                  <div class="col-md-5">
                    <input
                      class="form-control"
                      type="text"
                      placeholder="참가자를 검색하세요."
                      v-model="participant_account"
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
                    <tr
                      v-for="(participant, index) in getParticipants"
                      :key="index"
                    >
                      <th scope="row">{{ index + 1 }}</th>
                      <td>{{ participant.name }}</td>
                      <td>{{ participant.email }}</td>
                      <td>{{ participant.code_name }}</td>
                      <td>
                        <div v-if="index > 0">
                          <button
                            class="btn bg-gradient-danger"
                            type="button"
                            id="btn-delete"
                            @click="deleteParticipant(participant)"
                          >
                            Delete
                          </button>
                        </div>
                      </td>
                    </tr>
                  </tbody>
                </table>
                <div class="col-md-12">
                  <button
                    type="submit"
                    class="btn bg-gradient-dark w-100"
                    @click="createHandler"
                  >
                    Create Room
                  </button>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import DatePicker from 'vue2-datepicker';
import 'vue2-datepicker/index.css';
import { createRoom } from '@/api/rooms.js';
export default {
  name: 'RoomCreate',
  components: { DatePicker },
  data() {
    return {
      user: this.$store.state.users.login,
      datetime: '',
      roomName: '',
      description: '',
      participants: [
        {
          name: this.$store.state.users.login.username,
          email: this.$store.state.users.login.useremail,
          code_id: '001',
          code_name: 'Owner',
        },
      ],
      participant_account: '',
      roleSelected: '',
    };
  },
  computed: {
    getParticipants() {
      return this.participants;
    },
  },
  methods: {
    // participant_role(event) {
    //   console.log(`participant_role event:`, event);
    //   this.code_id = event.target.value;
    //   this.code_name = event.target.name;
    // },
    addParticipant() {
      let msg = '';
      let err = false;
      if (!this.participant_account) {
        msg = '사용자를 입력해주세요';
        err = true;
      }

      if (err) {
        alert(msg);
      } else {
        let participantName = '';
        console.log(`참가자 이메일 검색: ${this.participant_account}`);
        console.log('selected role: ', this.roleSelected.name);
        this.participants.push({
          name: participantName,
          email: this.participant_account,
          code_id: this.roleSelected.split('-')[0],
          code_name: this.roleSelected.split('-')[1],
        });
        console.log(`참가자 추가: ${this.participants}`);
      }
    },
    deleteParticipant(participant) {
      console.log('delete participant', participant);
      this.participants.forEach((index, element) => {
        console.log('delete element', element);
        if (element.email == participant.email) {
          console.log('same element', element.email);
          this.participants.splice(index);
        }
      });
    },

    createHandler() {
      let msg = '';
      let err = false;

      if (!this.name) {
        msg = '방 이름을 입력해주세요';
        err = true;
      } else if (!this.datetime) {
        msg = '날짜를 입력해주세요';
        err = true;
      } else if (!this.description) {
        msg = '방 설명을 입력해주세요';
        err = true;
      }

      //방장 추가
      this.participants.add({
        name: this.user.username,
        email: this.user.useremail,
        role: '001',
      });

      if (err) {
        alert(msg);
      } else {
        let roomData = {
          name: this.roomName,
          description: this.description,
          startTime: this.datetime,
          email: `${this.$store.state.users.login.useremail}`,
          person: this.participants,
        };

        createRoom(roomData)
          .then(({ status }) => {
            console.log(status);
            if (status != 200) {
              alert('오류 발생');
              return;
            }
          })
          .catch(() => {
            alert('error! catch');
          });
      }
    },
  },
};
</script>
