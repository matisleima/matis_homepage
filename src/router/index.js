import {createRouter, createWebHistory} from 'vue-router'
import HomeView from "@/views/HomeView.vue";
import LoginView from "@/views/LoginView.vue";
import ErrorView from "@/views/ErrorView.vue";

const routes = [
    {
        path: '/home',
        name: 'homeRoute',
        component: HomeView
    },
    {
        path: '/',
        name: 'loginRoute',
        component: LoginView
    },
    {
        path: '/error',
        name: 'errorRoute',
        component: ErrorView
    }

]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
