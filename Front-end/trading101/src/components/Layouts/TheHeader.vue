<template>
  <header>
    <a class="logo" href="/"> <img alt="logo" src=""/>
      <span class="title">
          {{ title }}
      </span>
    </a>
    <div class="header-right">
hi
    </div>
  </header>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";

@Options({
  components: {},
  props: {
    title: String,
  },
})
export default class TheHeader extends Vue {
  title!: string;
}
</script>
<style scoped>
/* Mobile first design */
header {
  width: 100%;
  height: 5rem;
  background-color: #003564;
  display: flex;
  justify-content: space-around;
  align-content: center;
}

header .title {
  color: white;
  margin: 0;
  font-size: 24px;
  font-weight: bold;
}

.header a:hover {
  color: mediumpurple;
}

.header-right {
  float: right;
  color: white;
}

/*
    desktop behavior --
  */
@media (min-width: 800px) {
  header,
  .logo {
    cursor: pointer;
    align-items: center;
  }
}

</style>