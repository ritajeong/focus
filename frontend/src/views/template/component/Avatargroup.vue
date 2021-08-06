<template>
  <div class="container py-6">
    <div class="row mt-5 text-center">
      <div class="col-12">
        <div class="avatar-group">
          <a
            href="javascript:;"
            class="avatar avatar-lg rounded-circle"
            data-bs-toggle="tooltip"
            data-bs-placement="bottom"
            title="Ryan Tompson"
          >
            <img alt="Image placeholder" src="../../assets/img/team-1.jpg" />
          </a>
          <a
            href="javascript:;"
            class="avatar avatar-lg rounded-circle"
            data-bs-toggle="tooltip"
            data-bs-placement="bottom"
            title="Romina Hadid"
          >
            <img alt="Image placeholder" src="../../assets/img/team-2.jpg" />
          </a>
          <a
            href="javascript:;"
            class="avatar avatar-lg rounded-circle"
            data-bs-toggle="tooltip"
            data-bs-placement="bottom"
            title="Alexander Smith"
          >
            <img alt="Image placeholder" src="../../assets/img/team-3.jpg" />
          </a>
          <a
            href="javascript:;"
            class="avatar avatar-lg rounded-circle"
            data-bs-toggle="tooltip"
            data-bs-placement="bottom"
            title="Jessica Doe"
          >
            <img alt="Image placeholder" src="../../assets/img/team-4.jpg" />
          </a>
        </div>
      </div>
    </div>
  </div>

  <!-- initialization script for tooltips -->
  <script>
    var tooltipTriggerList = [].slice.call(
      document.querySelectorAll('[data-bs-toggle="tooltip"]'),
    );
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
      return new bootstrap.Tooltip(tooltipTriggerEl);
    });
  </script>
</template>
