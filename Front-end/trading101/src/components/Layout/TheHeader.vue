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
    <the-tab> </the-tab>
  </v-app-bar>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
import TheTab from "./TheTab.vue";

@Component({
  components: {
    TheTab,
  },
})
export default class TheHeader extends Vue {}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss"></style>
