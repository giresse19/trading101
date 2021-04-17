import Vue from "vue";
import Router from "vue-router";
import HomePage from "@/views/Homepage.vue";
import Login from "@/views/Login.vue";
import SignUp from "@/views/SignUp.vue";
import About from "@/views/About.vue";


Vue.use(Router);

export default new Router({
    routes: [
        {
            path: "/",
            component: HomePage
        },
        {
            path: "/about",
            component: About
        },
        {
            path: "/SignUp",
            component: SignUp
        },
        {
            path: "/login",
            component: Login
        },

    ],
    mode: "history"
});
