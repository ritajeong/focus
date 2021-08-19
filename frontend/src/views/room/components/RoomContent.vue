<template>
  <section>
    <form role="form" id="contact-form" method="post" autocomplete="off">
      <div class="card-body">
        <div class="row">
          <div class="col-md-7">
            <label><h6>Room Name</h6></label>
            <div class="input-group mb-3">
              <input
                v-model="roomName"
                class="form-control"
                type="text"
                placeholder="멋진 이름을 지어주세요."
                :readonly="!isManager"
              />
            </div>
          </div>
          <div class="col-md-3 ps-3">
            <label><h6>Start Time</h6></label>
            <div>
              <date-picker
                v-model="datetime"
                type="datetime"
                :placeholder="datetime"
                format="YYYY-MM-DD HH:mm"
                :disabled-date="disabledBeforeDate"
                :disabled-time="disabledBeforeTime"
                :editable="isManager"
              ></date-picker>
            </div>
          </div>
        </div>
        <div class="form-group mb-4">
          <label><h6>Description</h6></label>
          <textarea
            v-model="description"
            type="text"
            class="form-control"
            rows="1"
            placeholder="방을 소개해주세요."
            :readonly="!isManager"
          ></textarea>
        </div>
        <label><h6>Participant List</h6></label>
        <div class="form-group mb-4" v-if="isManager">
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
                class="btn bg-gradient-dark"
                @click="addParticipant"
              >
                Add
              </button>
            </div>
          </div>
        </div>

        <div class="mb-4 row px-3">
          <table class="table table-striped">
            <thead>
              <tr>
                <th class="ps-3" scope="col">Num</th>
                <th class="ps-3" scope="col">Name</th>
                <th class="ps-3" scope="col">Email</th>
                <th class="ps-3" scope="col">Role</th>
                <th class="ps-3" scope="col"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(participant, index) in getParticipants" :key="index">
                <th scope="row" class="ps-3">{{ index + 1 }}</th>
                <td class="ps-3">{{ participant.name }}</td>
                <td class="ps-3">{{ participant.email }}</td>
                <td class="ps-3">{{ participant.codeId.codeName }}</td>
                <td>
                  <div v-if="index > 0 && isManager">
                    <button
                      class="btn btn-outline-danger text-danger py-1"
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

          <div class="col-md-12 justify-content-between" v-if="isManager">
            <span>
              <button
                type="button"
                class="btn bg-gradient-dark w-30 ms-8"
                @click="updateHandler"
              >
                Update Room
              </button>

              <button
                type="button"
                class="btn btn-outline-danger text-danger w-30 ms-3"
                data-bs-toggle="modal"
                data-bs-target="#modal-notification"
              >
                Delete Room
              </button>
            </span>
          </div>
        </div>
        <h3 class="text-center">Presentation Files</h3>

        <div v-if="files.length == 0 && !this.$store.state.rooms.room.endTime">
          <UploadDialog />
        </div>
        <div v-else>
          <RoomFiledetail></RoomFiledetail>
          <div class="text-center">
            <button
              class="btn btn-outline-danger text-danger"
              type="button"
              id="btn-delete"
              @click="deletefile()"
            >
              발표 자료 삭제
            </button>
          </div>
        </div>
      </div>
    </form>
    <RoomDeleteModal
      v-bind:roomId="this.$store.state.rooms.room.room_id"
    ></RoomDeleteModal>
  </section>
</template>

<script>
import Vue from 'vue';
import DatePicker from 'vue2-datepicker';
import 'vue2-datepicker/index.css';
import { updateRoom } from '@/api/rooms.js';
import { findUser } from '@/api/users.js';
import { deletetot } from '@/api/file.js';
import VueAlertify from 'vue-alertify';
import moment from 'moment';
import { mapGetters } from 'vuex';
import RoomDeleteModal from './RoomDeleteModal.vue';
import UploadDialog from './UploadDialog';
import RoomFiledetail from './RoomFiledetail.vue';
import { showfiledetail } from '@/api/file.js';

Vue.use(VueAlertify);

export default {
  name: 'RoomContent',
  components: {
    DatePicker,
    RoomDeleteModal,
    UploadDialog,
    RoomFiledetail,
  },
  data() {
    return {
      user: this.$store.state.users.login,
      datetime: this.$store.state.rooms.room.startTime,
      roomName: this.$store.state.rooms.room.name,
      description: this.$store.state.rooms.room.description,
      participants: this.$store.state.rooms.room.participants,
      participant: '',
      participantAccount: '',
      roleSelected: '',
      nowDateTime: moment(new Date()).format('YYYY-MM-DD HH:mm'),
      isManager: false,
      userid: this.$store.state.users.login.userid,
      roomid: this.$store.state.rooms.room.room_id,
      files: [],
    };
  },
  created() {
    window.scrollTo(0, 0);
    if (
      this.$store.state.rooms.room.manager_id ==
        this.$store.state.users.login.userid &&
      !this.$store.state.rooms.room.endTime
    ) {
      this.isManager = true;
    }
    const formData = new FormData();
    formData.append('user_id', this.userid);
    formData.append('room_id', this.roomid);
    showfiledetail(formData)
      .then(data => {
        this.files = data.data;
      })
      .catch(() => {
        console.log('error');
        this.$alertify.error('파일을 가져오지 못했습니다.');
      });
  },
  computed: {
    ...mapGetters(['users', 'room']),
    getParticipants() {
      return this.participants;
    },
  },
  methods: {
    deletefile() {
      const formDa = new FormData();
      formDa.append('user_id', this.userid);
      formDa.append('room_id', this.roomid);
      deletetot(formDa)
        .then(data => {
          console.log(data);
          this.$router.go();
        })
        .catch(() => {
          this.$alertify.error('error! catch');
        });
    },
    addParticipant() {
      let msg = '';
      if (!this.participantAccount || !this.roleSelected) {
        msg = '추가하려는 사용자 이메일 및 역할을 입력해주세요';
        this.$alertify.error(msg);
        return;
      }

      // 같은 계정 참가자 추가 안되게 함.
      const size = this.participants.length;

      for (let i = 0; i < size; i++) {
        if (this.participants[i].email == this.participantAccount) {
          this.$alertify.error('이미 등록된 사용자입니다.');
          return;
        }
      }

      this.getUsername().then(() => {
        if (!this.participants) {
          this.$alertify.error('사용자 게정이 없습니다.');
        } else {
          this.participants.push({
            name: this.participant.data.name,
            email: this.participantAccount,
            codeId: {
              codeId: this.roleSelected.split('-')[0],
              codeName: this.roleSelected.split('-')[1],
            },
          });
        }
      });
    },
    deleteParticipant(email) {
      this.participants.forEach((element, index) => {
        if (element.email == email) {
          this.participants.splice(index);
        }
      });
    },

    updateHandler() {
      let msg = '';
      let err = false;
      if (!this.roomName) {
        msg = '방 이름을 입력해주세요';
        err = true;
      } else if (!this.description) {
        msg = '방 설명을 입력해주세요';
        err = true;
      }

      if (err) {
        this.$alertify.error(msg);
        return;
      } else {
        let roomData = {
          user_id: this.user.userid,
          room_id: this.$store.state.rooms.room.room_id,
          name: this.roomName,
          description: this.description,
          startTime: this.datetime,
          participants: this.participants,
        };

        updateRoom(roomData)
          .then(({ status }) => {
            if (status != 200) {
              this.$alertify.error('방 정보 수정중 실패했습니다.');
              return;
            } else {
              this.$alertify.success('방 정보가 수정됐습니다.');
              this.$store.dispatch('rooms/setRoom', roomData);
              //this.$router.push('/dashboard/info');
            }
          })
          .catch(() => {
            this.$alertify.error('방 정보 수정 실패했습니다.');
          });
      }
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
          'HH:mm',
        )
      );
    },
    inputChanged() {
      this.$store.dispatch('GET_ALL_USERS', this.participantAccount);
    },
  },
};
</script>
