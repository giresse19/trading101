<template>
  <the-header title="Trading101"></the-header>
  <Homepage msg="Welcome to Your Vue.js + TypeScript App"/>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import Homepage from "./components/Homepage.vue";
import TheHeader from "./components/Layouts/TheHeader.vue";


@Options({
  components: {
    Homepage,
    TheHeader,
  },
})

export default class App extends Vue {
}
</script>

<style lang="scss">
#app {
  font-family: "Open Sans","Gill Sans MT","Gill Sans",Corbel,Arial,sans-serif;
  font-size: 14px;
  font-weight: 400;
  -webkit-font-smoothing: antialiased;
  //-moz-osx-font-smoothing: grayscale;

}
</style>
