<template>
  <div class="container">
    <div class="row py-7 text-center">
      <div>
        <button
          type="button"
          class="btn bg-gradient-secondary"
          data-bs-container="body"
          data-bs-toggle="popover"
          data-bs-placement="top"
          data-bs-content="That’s the main thing people are controlled by! Thoughts- their perception of themselves!"
        >
          Popover on top
        </button>

        <button
          type="button"
          class="btn bg-gradient-secondary"
          data-bs-container="body"
          data-bs-toggle="popover"
          data-bs-placement="right"
          data-bs-content="We’re not always in the position that we want to be at."
        >
          Popover on right
        </button>

        <button
          type="button"
          class="btn bg-gradient-secondary"
          data-bs-container="body"
          title="Popover with title"
          data-bs-toggle="popover"
          data-bs-placement="bottom"
          data-bs-content="A lot of people don’t appreciate the moment until it’s passed."
        >
          Popover on bottom
        </button>

        <button
          type="button"
          class="btn bg-gradient-secondary"
          data-bs-container="body"
          data-bs-toggle="popover"
          data-bs-placement="left"
          data-bs-content="It really matters and then like it really doesn’t matter. What matters is the people who are sparked by it."
        >
          Popover on left
        </button>
      </div>
    </div>
  </div>

  <!-- initialization script -->
  <script>
    var popoverTriggerList = [].slice.call(
      document.querySelectorAll('[data-bs-toggle="popover"]'),
    );
    var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
      return new bootstrap.Popover(popoverTriggerEl);
    });
  </script>
</template>
