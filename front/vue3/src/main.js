import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import { createPinia } from 'pinia';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import axios from 'axios';

// 配置Axios基础URL
axios.defaults.baseURL = 'http://localhost:8088';

const app = createApp(App);
app.use(router);
app.use(createPinia());
app.use(ElementPlus);
app.mount('#app');