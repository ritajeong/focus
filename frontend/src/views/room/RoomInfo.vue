<template>
  <div>
    <router-link to="/dashboard/modify">방정보수정</router-link><AppForm />
  </div>
</template>
<script>
import AppForm from '@/components/common/AppForm';
export default {
  name: 'RoomInfo',
  components: { AppForm },
};
</script>
