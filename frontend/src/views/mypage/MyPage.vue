<template>
  <div>
    <AppNav />
    <div>
      <router-view></router-view>
    </div>
    <AppFooter />
  </div>
</template>
<script>
import AppNav from '@/components/common/AppNav.vue';
import AppFooter from '@/components/common/AppFooter.vue';
export default {
  name: 'MyPage',
  components: { AppNav, AppFooter },
};
</script>
