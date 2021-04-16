<template>
  <v-carousel cycle height="725" hide-delimiter-background show-arrows-on-hover>
    <v-carousel-item
      v-for="(slide, i) in slides"
      :key="i"
      :src="slide.src"
      reverse-transition="fade-transition"
      transition="fade-transition"
    ></v-carousel-item>
  </v-carousel>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component
export default class TheCarousel extends Vue {
  slides: Array<any> = [
    {
      src: require("../images/carousel3.gif"),
    },
    {
      src: require("../images/carousel2.jpg"),
    },
    {
      src: require("../images/carousel4.jpg"),
    },
  ];
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.carousel-item-style {
  //background-color: yellow;
}
</style>
