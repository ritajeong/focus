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
                    <input class="form-control" type="text" />
                  </div>
                </div>
                <div class="col-md-6 ps-2">
                  <label>Start Time</label>
                  <div class="input-group">
                    <input
                      type="text"
                      class="form-control"
                      placeholder="ex) 20210901 2240"
                    />
                    <input
                      type="text"
                      id="datePicker"
                      class="form-control"
                      value="2019-06-27"
                    />
                    <div class="input-group">
                      <span class="input-group-text">
                        <svg
                          class="icon icon-xs"
                          fill="currentColor"
                          viewBox="0 0 20 20"
                          xmlns="http://www.w3.org/2000/svg"
                        >
                          <path
                            fill-rule="evenodd"
                            d="M6 2a1 1 0 00-1 1v1H4a2 2 0 00-2 2v10a2 2 0 002 2h12a2 2 0 002-2V6a2 2 0 00-2-2h-1V3a1 1 0 10-2 0v1H7V3a1 1 0 00-1-1zm0 5a1 1 0 000 2h8a1 1 0 100-2H6z"
                            clip-rule="evenodd"
                          ></path>
                        </svg>
                      </span>
                      <input
                        data-datepicker=""
                        class="form-control"
                        id="birthday"
                        type="text"
                        placeholder="dd/mm/yyyy"
                        required
                      />
                    </div>
                    <!-- time picker -->
                    <!--npm install --save luxon vue-datetime weekstart-->
                  </div>
                </div>
              </div>
              <div class="form-group mb-4">
                <label>Room Description</label>
                <textarea
                  type="text"
                  class="form-control"
                  rows="1"
                  placeholder="방을 소개해주세요!"
                ></textarea>
              </div>
              <div class="form-group mb-4">
                <label>Member List</label>
                <textarea
                  name="message"
                  class="form-control"
                  id="message"
                  rows="4"
                  placeholder="ex) "
                ></textarea>
              </div>
              <div class="row">
                <!-- <div class="col-md-12">
                  <div class="form-check form-switch mb-4">
                    <input
                      class="form-check-input"
                      type="checkbox"
                      id="flexSwitchCheckDefault"
                      checked=""
                    />
                    <label class="form-check-label" for="flexSwitchCheckDefault"
                      >I agree to the
                      <a href="javascript:;" class="text-dark"
                        ><u>Terms and Conditions</u></a
                      >.</label
                    >
                  </div>
                </div> -->
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
export default {
  name: 'RoomCreate',
  components: {},
};
</script>
