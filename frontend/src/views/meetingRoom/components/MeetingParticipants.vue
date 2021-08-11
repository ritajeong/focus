<template>
  <div class="d-flex flex-column">
    <MeetingParticipantsItem
      v-for="participant in participants"
      :participant="participant"
      :key="participant.name"
    />
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
  computed: {
    participants() {
      return this.$store.state.meetingRoom.participants;
    },
  },
  // : lifecycle hook
  mounted() {},
  // : methods
  methods: {},
};
</script>
