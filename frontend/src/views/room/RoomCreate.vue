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
                      v-model="date"
                      type="datetime"
                      format="YYYY-MM-DD hh:mm A"
                    ></date-picker>
                  </div>
                </div>
              </div>
              <div class="form-group mb-4">
                <label>Room Description</label>
                <textarea
                  type="text"
                  class="form-control"
                  rows="1"
                  placeholder="방을 소개해주세요."
                ></textarea>
              </div>

              <div class="form-group mb-4">
                <label>Member List</label>
                <div class="row">
                  <div class="col-md-5">
                    <input
                      class="form-control"
                      type="text"
                      placeholder="참가자를 검색하세요."
                      v-model="member"
                    />
                  </div>
                  <div class="col-md-5">
                    <select
                      name="role"
                      id="role"
                      class="form-select"
                      aria-label="Default select example"
                      v-model="role"
                    >
                      <option value="100">Presenter</option>
                      <option value="000">Normal</option>
                    </select>
                  </div>
                  <div class="col-md-2">
                    <button
                      type="submit"
                      class="btn bg-gradient-primary"
                      @click="addMember"
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
                    <tr>
                      <th scope="row">1</th>
                      <td>{{ user.username }}</td>
                      <td>{{ user.useremail }}</td>
                      <td>Owner</td>
                      <td></td>
                    </tr>
                    <tr v-for="(member, index) in members" :key="index">
                      <th scope="row">{{ index }}</th>
                      <td>{{ member.name }}</td>
                      <td>{{ member.email }}</td>
                      <td>{{ member.role }}</td>
                      <td>
                        <button
                          class="btn bg-gradient-danger"
                          type="button"
                          id="btn-delete"
                        >
                          Delete
                        </button>
                      </td>
                    </tr>
                  </tbody>
                </table>
                <div class="col-md-12">
                  <button type="submit" class="btn bg-gradient-dark w-100">
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

export default {
  name: 'RoomCreate',
  computed: {
    getMembers() {
      return this.members;
    },
  },
  methods: {
    addMember() {
      let msg = '';
      let err = false;
      if (!this.member) {
        msg = '사용자를 입력해주세요';
        err = true;
      }

      if (err) {
        alert(msg);
      }
    },
  },
  components: { DatePicker },
  data() {
    return {
      //YYYY-MM-DD hh:mm A
      user: this.$store.state.users.login,
      date: '',
      members: [
        {
          name: '',
          email: '',
          role: '',
        },
      ],
    };
  },
};
</script>
