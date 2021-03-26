import {createApp} from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import store from "./store";
import BaseCard from "./components/UI/BaseCard.vue"

// import { MdButton, MdContent, MdTabs } from "vue-material/dist/components"

const app = createApp(App);

app.component("base-card", BaseCard);
app.mount("#app");
