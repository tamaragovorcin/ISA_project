import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import PswRegistration from '../views/PswRegistration.vue'
import Pharmacy from '../views/Pharmacy.vue'

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
	path: '/pharmacy',
	name: 'Pharmacy',
	component: Pharmacy
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
