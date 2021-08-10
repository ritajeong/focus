<template>
  <div class="d-flex flex-column">
    <MeetingParticipantsItem />
    <MeetingParticipantsItem />
    <MeetingParticipantsItem />
    <MeetingParticipantsItem />
    <MeetingParticipantsItem />
  </div>
</template>

<script>
import MeetingParticipantsItem from './MeetingParticipantsItem.vue';

export default {
  name: 'MeetingParticipants',
  components: { MeetingParticipantsItem },
  // : props
  props: {},
  // : data
  data() {
    return {};
  },
  // : computed
  computed: {},
  // : lifecycle hook
  mounted() {},
  // : methods
  methods: {},
};
</script>
