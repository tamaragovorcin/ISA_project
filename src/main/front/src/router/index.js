import Vue from 'vue'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import PswRegistration from '../views/PswRegistration.vue'
import PharmacyVue from '../views/PharmacyVue.vue'
import ConcretePharmacy from '../views/ConcretePharmacy.vue'

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/pswregistration',
    name: 'PswRegistration',
    component: PswRegistration
  },
  {
	path: '/pharmacyVue',
	name: 'PharmacyVue',
	component: PharmacyVue
  },
  {
	path: '/concretePharmacy/:id',
	name: 'ConcretePharmacy',
	component: ConcretePharmacy
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
