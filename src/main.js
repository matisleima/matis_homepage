import { createApp } from 'vue';
import App from './App.vue';
import router from './router'; // import your router configuration
//
// createApp(App)
//     .use(router) // use the router
//     .mount('#app');


import VueAxios from "vue-axios";
import axios from "axios";
import "@/assets/css/my-style.css"



const app = createApp(App)

app.use(router)
app.use(VueAxios, axios)
app.mount('#app')