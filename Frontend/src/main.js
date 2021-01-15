import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false
Vue.use(VueAxios, axios)
const baseURL = 'http://localhost:8082/api';
if (typeof baseURL !== 'undefined') {
  Vue.axios.defaults.baseURL = baseURL;
}

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
