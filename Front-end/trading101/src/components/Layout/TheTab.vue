<template>
  <v-card>
    <v-tabs class="tabs">
      <v-tab v-for="tab of tabs" :key="tab.id" :to="tab.route" exact>
        {{ tab.name }}
      </v-tab>

      <v-tab-item v-for="tab of tabs" :key="tab.id" :value="tab.route">
        <router-view></router-view>
      </v-tab-item>
    </v-tabs>
    <router-view></router-view>
  </v-card>
</template>

<script lang="ts">
import {Component, Prop, Vue} from "vue-property-decorator";

@Component
export default class TheTab extends Vue {
  tabs: Array<any> = [
    {id: 1, name: "Home", route: `/home`},
    {id: 2, name: "About Us", route: `/about`},
    {id: 3, name: "Sign Up", route: `/signup`},
    {id: 4, name: "login", route: `/login`},
  ];
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.tabs {
}
</style>

