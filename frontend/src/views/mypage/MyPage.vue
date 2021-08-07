<template>
  <div>
    <AppNav />
    <div style="padding-top: 60px">
      <router-view></router-view>
    </div>
    <AppFooter />
  </div>
</template>
<script>
import AppNav from '@/components/common/AppNav.vue';
export default {
  name: 'MyPage',
  components: { AppNav },
};
</script>
