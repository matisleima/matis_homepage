import {createRouter, createWebHistory} from 'vue-router'
import HomeView from "@/views/HomeView.vue";
import ArtView from "@/views/ArtView.vue";
import BioView from "@/views/BioView.vue";
import ContactView from "@/views/ContactView.vue";
import GamesView from "@/views/GamesView.vue";
import MusicView from "@/views/MusicView.vue";
import PortfolioView from "@/views/PortfolioView.vue";
import ShopView from "@/views/ShopView.vue";
import ErrorView from "@/views/ErrorView.vue";

const routes = [
    {
        path: '/',
        name: 'homeRoute',
        component: HomeView
    },
    {
        path: '/art',
        name: 'artRoute',
        component: ArtView
    },
    {
        path: '/bio',
        name: 'bioRoute',
        component: BioView
    },
    {
        path: '/contact',
        name: 'contactRoute',
        component: ContactView
    },
    {
        path: '/error',
        name: 'errorRoute',
        component: ErrorView
    },
    {
        path: '/games',
        name: 'gamesRoute',
        component: GamesView
    },
    {
        path: '/music',
        name: 'musicRoute',
        component: MusicView
    },
    {
        path: '/portfolio',
        name: 'portfolioRoute',
        component: PortfolioView
    },
    {
        path: '/shop',
        name: 'shopRoute',
        component: ShopView
    }

]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
