// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Page2 from './Page2.vue'
import axios from 'axios'
import Vuex from 'vuex'

Vue.prototype.$http = axios

Vue.config.productionTip = false
Vue.use(Vuex)

/* eslint-disable no-new */
new Vue({
  el: '#contents',
  render: h => h(Page2)
})
