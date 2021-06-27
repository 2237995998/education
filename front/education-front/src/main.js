import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import "./axios"
import Print from 'vue-print-nb'

Vue.use(Print);  //注册
Vue.config.productionTip = false
Vue.prototype.$axios = axios
Vue.use(ElementUI);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
