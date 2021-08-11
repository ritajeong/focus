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
                    />
                  </div>
                  <div class="col-md-5">
                    <select
                      name="role"
                      id="role"
                      class="form-select"
                      aria-label="Default select example"
                    >
                      <option value="100">Presenter</option>
                      <option value="000">Normal</option>
                    </select>
                  </div>
                  <div class="col-md-2">
                    <button type="submit" class="btn bg-gradient-primary">
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
                      <td>Mark</td>
                      <td>Mark@com</td>
                      <td>Presenter</td>
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
                    <tr>
                      <th scope="row">2</th>
                      <td>Jacob</td>
                      <td>Jacob@com</td>
                      <td>Normal</td>
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
  components: { DatePicker },
  data() {
    return {
      date: {
        //YYYY-MM-DD hh:mm A
      },
    };
  },
};
</script>
