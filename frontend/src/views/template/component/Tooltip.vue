<template>
  <div class="container">
    <div class="row py-8 text-center">
      <div>
        <button
          type="button"
          class="btn bg-gradient-secondary"
          data-bs-toggle="tooltip"
          data-bs-placement="top"
          title="Tooltip on top"
        >
          Tooltip on top
        </button>
        <button
          type="button"
          class="btn bg-gradient-secondary"
          data-bs-toggle="tooltip"
          data-bs-placement="right"
          title="Tooltip on right"
        >
          Tooltip on right
        </button>
        <button
          type="button"
          class="btn bg-gradient-secondary"
          data-bs-toggle="tooltip"
          data-bs-placement="bottom"
          title="Tooltip on bottom"
        >
          Tooltip on bottom
        </button>
        <button
          type="button"
          class="btn bg-gradient-secondary"
          data-bs-toggle="tooltip"
          data-bs-placement="left"
          title="Tooltip on left"
        >
          Tooltip on left
        </button>
      </div>
    </div>
  </div>

  <!-- initialization script -->
  <script>
    var tooltipTriggerList = [].slice.call(
      document.querySelectorAll('[data-bs-toggle="tooltip"]'),
    );
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
      return new bootstrap.Tooltip(tooltipTriggerEl);
    });
  </script>
</template>
