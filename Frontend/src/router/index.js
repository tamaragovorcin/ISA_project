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
import SystemAdminProfile from '../views/systemAdmin/SystemAdminProfile.vue'
import RegisterPharmacy from '../views/systemAdmin/RegisterPharmacy.vue'
import RegisterPharmacyAdmin from '../views/systemAdmin/RegisterPharmacyAdmin.vue'
import RegisterSupplier from '../views/systemAdmin/RegisterSupplier.vue'
import RegisterDermatologist from '../views/systemAdmin/RegisterDermatologist.vue'
import RegisterSystemAdmin from '../views/systemAdmin/RegisterSystemAdmin.vue'
import MyPharmacy from '../views/pharmacyAdmin/MyPharmacy.vue'
import ActionsAndBenefits from '../views/pharmacyAdmin/ActionsAndBenefits.vue'
import SupplierProfile from '../views/supplier/SupplierProfile.vue'
import SupplierProfileInfo from '../views/supplier/SupplierProfileInfo.vue'
import dermatologistAppointments from '../views/patient/dermatologistAppointments.vue'
import AddMedicine from '../views/systemAdmin/AddMedicine.vue'
import LoyaltyProgram from '../views/systemAdmin/LoyaltyProgram.vue'
import SubscriptionsToPharmacies from '../views/patient/SubscriptionsToPharmacies.vue'
import SystemAdminMedicationSearch from '../views/systemAdmin/SystemAdminMedicationSearch.vue'
import PhAdminProfileUpdate from '../views/pharmacyAdmin/PhAdminProfileUpdate.vue'
import PharmacyDermatologists from '../views/pharmacyAdmin/PharmacyDermatologists.vue'
import PharmacyPharmacists from '../views/pharmacyAdmin/PharmacyPharmacists.vue'
import AddPharmacist from '../views/pharmacyAdmin/AddPharmacist.vue'
import Complaints from '../views/systemAdmin/Complaints.vue'

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
    },
    {

      path: '/supplierProfileInfo',
      name: 'SupplierProfileInfo',
      component: SupplierProfileInfo
    },
    {
      path: '/dermatologistAppointments',
      name: 'dermatologistAppointments',
      component: dermatologistAppointments
    }, 
    {
      path: '/addMedicine',
      name: 'AddMedicine',
      component: AddMedicine
    },
    {
      path: '/loyaltyProgram',
      name: 'LoyaltyProgram',
      component: LoyaltyProgram
    },
    {
      path: '/subscriptionsToPharmacies',
      name: 'SubscriptionsToPharmacies',
      component: SubscriptionsToPharmacies
    },
    {
      path: '/systemAdminMedicationSearch',
      name: 'SystemAdminMedicationSearch',
      component: SystemAdminMedicationSearch
    },
    {
      path: '/phAdminProfileUpdate',
      name: 'PhAdminProfileUpdate',
      component: PhAdminProfileUpdate
    },
    
    {
      path: '/pharmacyDermatologists',
      name: 'PharmacyDermatologists',
      component: PharmacyDermatologists
    },  
    {
      path: '/pharmacyPharmacists',
      name: 'PharmacyPharmacists',
      component: PharmacyPharmacists
    },
    {
      path: '/addPharmacist',
      name: 'AddPharmacist',
      component: AddPharmacist
    },
    {
      path: '/complaints',
      name: 'Complaints',
      component: Complaints
    },
  
]
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
