import Vue from 'vue'
import App from './App.vue'
import { router } from './routes/index'; 
import store  from './store'



//const BACKEND_PORT = process.env.BACKEND_PORT === null ? '' : `:${process.env.BACKEND_PORT}`
//const BACKEND_DOMAIN = process.env.BACKEND_DOMAIN === null ? `${location.protocol}//${location.hostname}` : process.env.BACKEND_DOMAIN
//axios.defaults.baseURL = `${BACKEND_DOMAIN}${BACKEND_PORT}`

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router,
  store,
}).$mount('#app')
