<template>
  <div class="fixed-plugin">
    <!--열기버튼-->
    <a class="fixed-plugin-button text-dark position-fixed px-3 py-2">
      <i class="fa fa-cog py-2"> </i>
    </a>
    <div class="card shadow-lg">
      <div class="card-header pb-0 pt-3">
        <div class="float-start">
          <h5 class="mt-3 mb-0">header</h5>
          <p>p</p>
        </div>
        <!--닫기버튼-->
        <div class="float-end mt-4">
          <button class="btn btn-link text-dark p-0 fixed-plugin-close-button">
            <i class="fa fa-close"></i>
          </button>
        </div>
        <!-- 닫기버튼 끝 -->
        <!-- 사이드탭 바디-->
      </div>
      <hr class="horizontal dark my-1" />
      <div class="card-body pt-sm-3 pt-0">
        <div>
          <h6 class="mb-0">body</h6>
        </div>
        <!--설명-->
        <div class="mt-3">
          <h6 class="mb-0">description</h6>
          <p class="text-sm">p</p>
        </div>
        <!--탭-->
        <div class="d-flex">
          <button
            class="btn bg-gradient-primary w-100 px-3 mb-2 active"
            data-class="bg-transparent"
          >
            <!--onclick="sidebarType(this)"-->
            Tap
          </button>
          <button
            class="btn bg-gradient-primary w-100 px-3 mb-2 ms-2"
            data-class="bg-white"
          >
            <!--onclick="sidebarType(this)"-->
            Tap
          </button>
        </div>
        <p class="text-sm d-xl-none d-block mt-2">p</p>
        <!-- 토글-->
        <div class="mt-3">
          <h6 class="mb-0">Toggle</h6>
        </div>
        <div class="form-check form-switch ps-0">
          <input
            class="form-check-input mt-1 ms-auto"
            type="checkbox"
            id="navbarFixed"
          />
          <!--onclick="navbarFixed(this)"-->
        </div>
        <!--긴 버튼-->
        <hr class="horizontal dark my-sm-4" />
        <a class="btn bg-gradient-dark w-100" href="#">Free Download</a>
        <a class="btn btn-outline-dark w-100" href="#">View documentation</a>
        <!--작은 버튼-->
        <div class="w-100 text-center">
          <h6 class="mt-3">ment</h6>
          <a href="#" class="btn btn-dark mb-0 me-2" target="_blank"> btn </a>
          <a href="#" class="btn btn-dark mb-0 me-2" target="_blank"> btn </a>
        </div>
      </div>
    </div>
  </div>
</template>
