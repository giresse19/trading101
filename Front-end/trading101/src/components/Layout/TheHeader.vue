<template>
  <v-app-bar app color="primary" dark>
    <div class="d-flex align-center">
      <v-img
        alt="Trading101 logo"
        class="shrink mr-2"
        contain
        src=""
        transition="scale-transition"
        width="40"
      />
      <span style="cursor: pointer">TRADING101</span>
    </div>
    <v-spacer></v-spacer>
    <v-card>
      <v-tabs class="tabs">
        <v-tab v-for="tab of tabs" :key="tab.id" :to="tab.route" exact>
          {{ tab.name }}
        </v-tab>

        <v-tab-item v-for="tab of tabs" :key="tab.id" :value="tab.route">
          <router-view></router-view>
        </v-tab-item>
      </v-tabs>
    </v-card>
  </v-app-bar>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import Homepage from "@/views/Homepage.vue";


@Component()
export default class TheHeader extends Vue {
  tabs: Array<any> = [
    {id: 1, name: "Home", route: `/home`},
    {id: 2, name: "About Us", route: `/about`},
    {id: 3, name: "Sign Up", route: `/signup`},
    {id: 4, name: "login", route: `/login`},
  ];
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss"></style>
