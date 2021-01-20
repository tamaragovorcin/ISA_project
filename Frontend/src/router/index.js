import Vue from 'vue'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import VueRouter from 'vue-router'
import Home1 from '../views/psw/Home1.vue'
import PswRegistration from '../views/psw/PswRegistration.vue'
import PharmacyVue from '../views/psw/PharmacyVue.vue'
import ConcretePharmacy from '../views/psw/ConcretePharmacy.vue'
import IsaHomePage from '../views/home/IsaHomePage.vue'
import Login from '../views/home/Login.vue'
import Registration from '../views/home/Registration.vue'
import PatientProfile from '../views/patient/PatientProfile.vue'
import PharmacyAdminProfile from '../views/pharmacyAdmin/PharmacyAdminProfile.vue'
import Order from '../views/pharmacyAdmin/Order.vue'
import PharmacyProfile from '../views/PharmacyProfile.vue'
import MyProfile from '../views/patient/MyProfile.vue'
import UpdateProfilePatient from '../views/patient/UpdateProfilePatient.vue'
import MedicationReservation from '../views/patient/MedicationReservation.vue'
import ShowPharmaciesPatient from '../views/patient/ShowPharmaciesPatient.vue'


Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home1',
    component: Home1
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
  {
    path: '/isaHomePage',
    name: 'IsaHomePage',
    component: IsaHomePage
    },
  {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/registration',
      name: 'Registration',
      component: Registration
    },
    {
      path: '/patientProfile',
      name: 'PatientProfile',
      component: PatientProfile
    },
    {
      path: '/pharmacyAdminProfile',
      name: 'PharmacyAdminProfile',
      component: PharmacyAdminProfile
    },
    {
      path: '/order',
      name: 'Order',
      component: Order
    },
    {
      path: '/pharmacyProfile',
      name: 'PharmacyProfile',
      component: PharmacyProfile
    },
    {
      path: '/myProfilePatient',
      name: 'MyProfile',
      component: MyProfile
    },
    {
      path: '/updateProfilePatient',
      name: 'UpdateProfilePatient',
      component: UpdateProfilePatient
    },
    {
      path: '/medicationReservation',
      name: 'medicationReservation',
      component: MedicationReservation
    },
    {
      path: '/showPharmaciesPatient',
      name: 'showPharmaciesPatient',
      component: ShowPharmaciesPatient
    },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
