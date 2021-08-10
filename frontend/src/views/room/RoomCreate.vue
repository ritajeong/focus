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
                    <input class="form-control" placeholder="" type="text" />
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
                    <!-- time picker -->
                    <!--npm install --save luxon vue-datetime weekstart-->
                  </div>
                </div>
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
