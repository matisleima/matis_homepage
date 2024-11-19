import { createApp } from 'vue';
import App from './App.vue';
import router from './router'; // import your router configuration
import VCalendar from 'v-calendar';
import 'v-calendar/style.css';

// Use plugin with optional defaults
app.use(VCalendar, {})



import VueAxios from "vue-axios";
import axios from "axios";
import "@/assets/css/my-style.css"



const app = createApp(App)

app.use(router)
app.use(VueAxios, axios)
app.mount('#app')