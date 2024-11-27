import { createApp } from 'vue';
import App from './App.vue';
import router from './router'; // import your router configuration
import VueAxios from "vue-axios";
import axios from "axios";
import "@/assets/css/my-style.css";

// Create the app instance
const app = createApp(App);

// Use plugins
app.use(router);
app.use(VueAxios, axios);

// Mount the app
app.mount('#app');
