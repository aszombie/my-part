import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { createVuetify } from 'vuetify'
// import 'vuetify/styles'
import App from './App.vue'
import router from './router/index.js'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import '@fortawesome/fontawesome-free/css/all.css';
import './assets/style.css';
import $ from 'jquery'
import pinia from './plugins/pinia'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css'

import 'bootstrap-icons/font/bootstrap-icons.css';
createApp(App)
    .use(router)
    .use(pinia)
    .use(Antd)
    // .use(vuetify)
    .mount('#app')