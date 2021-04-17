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
        primary: "#1E1E1E",
        secondary: "#0099DA",
        accent: "#08C792",
        success: "#08C792",
        error: "#ED2939",
      },
      dark: {
        primary: "#1E1E1E",
        secondary: "#0099DA",
        accent: "#08C792",
        success: "#08C792",
        error: "#ED2939",
      },
    },
  },
});
