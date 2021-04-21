import Vue from "vue";
import Vuetify from "vuetify/lib/framework";

Vue.use(Vuetify);

export default new Vuetify({
  icons: {
    iconfont: "md",
  },
  theme: {
    options: {
      customProperties: true,
    },
    themes: {
      light: {
        primary: "#f3e9d2",
        secondary: "#0099DA",
        accent: "#08C792",
        success: "#08C792",
        error: "#ED2939",
      },

    },
  },
});
