import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: "/",
            name: 'home',
            component: () => import('../views/Homepage.vue')
        },
        {
            path: "/about",
            name: 'about',
            component: () => import('../views/About.vue')
        },
        {
            path: "/SignUp",
            name: 'Signup',
            component: () => import('../views/SignUp.vue')
        },
        {
            path: "/login",
            name: 'login',
            component: () => import('../views/Login.vue')
        },
        {
            path: '/forgot-password',
            name: 'forgot-password',
            component: () => import('../views/forgot-password.vue')
        }

    ],
    mode: "history"
});
