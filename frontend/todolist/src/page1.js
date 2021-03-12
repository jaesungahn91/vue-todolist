// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Page1 from './Page1.vue'
import axios from 'axios'
import Vuex from 'vuex'
Vue.prototype.$http = axios

Vue.config.productionTip = false
Vue.use(Vuex)

/* eslint-disable no-new */
new Vue({
  el: '#category',
  render: h => h(Page1)
})
