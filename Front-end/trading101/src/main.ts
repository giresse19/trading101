import Vue from "vue";
import App from "./App.vue";
import router from "./plugins/router"
import { makeStore } from './store';

import vuetify from "./plugins/vuetify";
import "material-design-icons-iconfont/dist/material-design-icons.css";

import '../src/assets/css/main.css'


Vue.config.productionTip = false;
const store = makeStore();

new Vue({
    store,
    router,
    vuetify,
    render: (h) => h(App),
}).$mount("#app");
