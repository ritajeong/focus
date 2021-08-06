<template>
  <!-- Start dropdowns & dropups -->
  <div class="container py-7">
    <div class="row">
      <div class="row">
        <div
          class="
            col-lg-4
            ms-lg-auto
            col-md-6 col-sm-3
            d-flex
            justify-content-center
          "
        >
          <div class="dropdown">
            <button
              class="btn bg-gradient-primary dropdown-toggle"
              type="button"
              id="dropdownMenuButton"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Dropdown button
            </button>
            <ul
              class="dropdown-menu px-2 py-3"
              aria-labelledby="dropdownMenuButton"
            >
              <li>
                <a class="dropdown-item border-radius-md" href="javascript:;"
                  >Action</a
                >
              </li>
              <li>
                <a class="dropdown-item border-radius-md" href="javascript:;"
                  >Another action</a
                >
              </li>
              <li>
                <a class="dropdown-item border-radius-md" href="javascript:;"
                  >Something else here</a
                >
              </li>
            </ul>
          </div>
        </div>
        <div
          class="
            col-lg-4
            me-lg-auto
            col-md-6 col-sm-3
            d-flex
            justify-content-center
          "
        >
          <div class="btn-group dropup mt-7">
            <button
              type="button"
              class="btn bg-gradient-primary dropdown-toggle"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Dropup
            </button>
            <ul
              class="dropdown-menu px-2 py-3"
              aria-labelledby="dropdownMenuButton"
            >
              <li>
                <a class="dropdown-item border-radius-md" href="javascript:;"
                  >Action</a
                >
              </li>
              <li>
                <a class="dropdown-item border-radius-md" href="javascript:;"
                  >Another action</a
                >
              </li>
              <li>
                <a class="dropdown-item border-radius-md" href="javascript:;"
                  >Something else here</a
                >
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--   end dropdowns & dropups -->
</template>
