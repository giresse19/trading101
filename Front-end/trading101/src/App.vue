<template>
  <v-card id="app" data-app>
    <v-toolbar
        dark
        flat
    >
      <div>
        <router-link
            to="/"
            tag="span"
            style="cursor: pointer"
        >
          <v-app-bar-title> {{ appName }}</v-app-bar-title>
        </router-link>
      </div>
      <v-spacer></v-spacer>

      <v-card>
        <template>
          <v-tabs class="tabs" v-model="activeTab">
            <v-tab v-for="tab of tabs"
                   :key="tab.id"
                   :to="tab.route" exact
            >
              {{ tab.name }}
            </v-tab>
          </v-tabs>
        </template>
      </v-card>
    </v-toolbar>

    <v-tabs-items  v-model="activeTab">
      <v-tab-item
          v-for="tab of tabs"
          :key="tab.id"
          :value="tab.route"
      >
        <router-view></router-view>
      </v-tab-item>
    </v-tabs-items>
  </v-card>
</template>

<script lang="ts">
import {Component, Prop, Vue} from "vue-property-decorator";
import Homepage from "@/views/Homepage.vue";

@Component({
  components: {
    Homepage
  },
})
export default class App extends Vue {
  @Prop(String)  id!: string;
  private activeTab = `/`;
  private appName = "TRADING101";

  tabs: Array<any> = [
    {id: 1, name: "Home", route: `/`},
    {id: 2, name: "About Us", route: `/about`},
    {id: 3, name: "Sign Up", route: `/signup`},
    {id: 4, name: "login", route: `/login`},
  ];

}
</script>

<style lang="scss">
#app {
  font-family: "Open Sans", Arial, Helvetica, sans-serif;
  font-style: normal;
  font-weight: normal;
}


a{
  text-decoration: none;
}
.app-tabs{
  display: flex;
  justify-content: space-between;
}
.pa-2 {
  height: 100%;
  width: 100%;
  border-width: thin;
  display: grid;
  outline: none;
  text-decoration: none;
  transition-property: box-shadow, opacity;
  overflow-wrap: break-word;
  position: relative;
  white-space: normal;
}

.right-body {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 2px;
  padding: 4px;
}

.btn-style{
  color: #114b5f;
}
</style>
