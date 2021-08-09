<template>
  <div class="container py-7">
    <div class="row mt-5">
      <div class="col-sm-3 col-6 mx-auto">
        <!-- Button trigger modal -->
        <button
          type="button"
          class="btn bg-gradient-primary"
          data-bs-toggle="modal"
          data-bs-target="#exampleModal"
        >
          Launch demo modal
        </button>

        <!-- Modal -->
        <div
          class="modal fade"
          id="exampleModal"
          tabindex="-1"
          aria-labelledby="exampleModalLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">
                  Your modal title
                </h5>
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <div class="modal-body">
                Society has put up so many boundaries, so many limitations on
                what’s right and wrong that it’s almost impossible to get a pure
                thought out.
                <br /><br />
                It’s like a little kid, a little boy, looking at colors, and no
                one told him what colors are good, before somebody tells you you
                shouldn’t like pink because that’s for girls, or you’d instantly
                become a gay two-year-old.
              </div>
              <div class="modal-footer justify-content-between">
                <button
                  type="button"
                  class="btn bg-gradient-dark"
                  data-bs-dismiss="modal"
                >
                  Close
                </button>
                <button type="button" class="btn bg-gradient-primary">
                  Save changes
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
