<template>
  <div>
    <h3>RoomSearch / RoomFilter / card view, list view 전환 버튼(v-show)</h3>
    <br />
    <br />
    <br />
    <h2>Now</h2>
    <br />
    <br />
    <br />
    <br />
    <h2>CardView : RoomCardGroup, RoomCardItem</h2>
    <h2>ListView : RoomListGroup, RoomListItem</h2>
    <h2><router-link to="/dashboard/info"> room info</router-link></h2>
    <br />
    <br />
    <br />
    <h2 align="right">
      <router-link to="/dashboard/create"> create room</router-link>
    </h2>
    <h2 align="right">
      <router-link to="/dashboard/tutorial"> tutorial </router-link>
    </h2>
  </div>
</template>

<script>
export default {
  name: 'RoomDashboard',
  components: {},
};
</script>
