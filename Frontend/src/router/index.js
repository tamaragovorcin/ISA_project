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
import PharmacistProfile from '../views/pharmacist/PharmacistProfile.vue'
import PharmacistClients from '../views/pharmacist/PharmacistClients.vue'
import NoteAboutConsulting from '../views/pharmacist/NoteAboutConsulting.vue'
import PharmacistHomePage from '../views/pharmacist/PharmacistHomePage.vue'
import DermatologistHomePage from '../views/dermatologist/DermatologistHomePage.vue'
import PharmacyAdminProfile from '../views/pharmacyAdmin/PharmacyAdminProfile.vue'
import Order from '../views/pharmacyAdmin/Order.vue'
import PharmacyProfile from '../views/PharmacyProfile.vue'
import SystemAdminProfile from '../views/systemAdmin/SystemAdminProfile.vue'
import RegisterPharmacy from '../views/systemAdmin/RegisterPharmacy.vue'
import RegisterPharmacyAdmin from '../views/systemAdmin/RegisterPharmacyAdmin.vue'
import RegisterSupplier from '../views/systemAdmin/RegisterSupplier.vue'
import RegisterDermatologist from '../views/systemAdmin/RegisterDermatologist.vue'
import RegisterSystemAdmin from '../views/systemAdmin/RegisterSystemAdmin.vue'
import AddNewPharmacist from '../views/pharmacyAdmin/AddNewPharmacist.vue'
import MyPharmacy from '../views/pharmacyAdmin/MyPharmacy.vue'
import ActionsAndBenefits from '../views/pharmacyAdmin/ActionsAndBenefits.vue'
import SupplierProfile from '../views/supplier/SupplierProfile.vue'


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
      path: '/pharmacistProfile',
      name: 'PharmacistProfile',
      component: PharmacistProfile
    },
    {
      path: '/pharmacistClients',
      name: 'PharmacistClients',
      component: PharmacistClients
    },
    {
      path: '/noteConsultig',
      name: 'NoteAboutConsulting',
      component: NoteAboutConsulting
    },
    {
      path: '/pharmacistHomePage',
      name: 'PharmacistHomePage',
      component: PharmacistHomePage
    },
    {
      path: '/dermatologistHomePage',
      name: 'DermatologistHomePage',
      component: DermatologistHomePage
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
      path: '/systemAdminProfile',
      name: 'SystemAdminProfile',
      component: SystemAdminProfile
    }
    ,
    {
      path: '/registerPharmacy',
      name: 'RegisterPharmacy',
      component: RegisterPharmacy
    }
    ,
    {
      path: '/registerPharmacyAdmin',
      name: 'RegisterPharmacyAdmin',
      component: RegisterPharmacyAdmin
    }
    ,
    {
      path: '/registerSupplier',
      name: 'RegisterSupplier',
      component: RegisterSupplier
    }
    ,
    {
      path: '/registerDermatologist',
      name: 'RegisterDermatologist',
      component: RegisterDermatologist
    },
    {
      path: '/registerSystemAdmin',
      name: 'RegisterSystemAdmin',
      component: RegisterSystemAdmin
    },
    {
      path: '/addNewPharmacist',
      name: 'AddNewPharmacist',
      component: AddNewPharmacist
    },
    {
      path: '/myPharmacy',
      name: 'MyPharmacy',
      component: MyPharmacy
    },
    {
      path: '/actionsAndBenefits',
      name: 'ActionsAndBenefits',
      component: ActionsAndBenefits
    },
    {
      path: '/supplierProfile',
      name: 'SupplierProfile',
      component: SupplierProfile
    }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
