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
import SchedulingConsulting from '../views/pharmacist/SchedulingConsulting.vue'
import CreateRequestVacation from '../views/pharmacist/CreateRequestVacation.vue'
import PharmacistUpdateProfile from '../views/pharmacist/PharmacistUpdateProfile.vue'
import WorkingHoursPharmacist from '../views/pharmacist/WorkingHoursPharmacist.vue'
import DermatologistHomePage from '../views/dermatologist/DermatologistHomePage.vue'
import DermatologistClients from '../views/dermatologist/DermatologistClients.vue'
import NoteAboutExamination from '../views/dermatologist/NoteAboutExamination.vue'
import DermatologistProfile from '../views/dermatologist/DermatologistProfile.vue'
import CreateRequestHoliday from '../views/dermatologist/CreateRequestHoliday.vue'
import SchedulingExamination from '../views/dermatologist/SchedulingExamination.vue'
import DermatologistUpdateProfile from '../views/dermatologist/DermatologistUpdateProfile.vue'
import PharmacyAdminProfile from '../views/pharmacyAdmin/PharmacyAdminProfile.vue'
import Order from '../views/pharmacyAdmin/Order.vue'
import PharmacyProfile from '../views/PharmacyProfile.vue'
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
import PharmacistConsultation from '../views/patient/PharmacistConsultation.vue'
import AddMedicine from '../views/systemAdmin/AddMedicine.vue'
import LoyaltyProgram from '../views/systemAdmin/LoyaltyProgram.vue'
import SubscriptionsToPharmacies from '../views/patient/SubscriptionsToPharmacies.vue'
import SystemAdminMedicationSearch from '../views/systemAdmin/SystemAdminMedicationSearch.vue'
import PhAdminProfileUpdate from '../views/pharmacyAdmin/PhAdminProfileUpdate.vue'
import PharmacyDermatologists from '../views/pharmacyAdmin/PharmacyDermatologists.vue'
import PharmacyPharmacists from '../views/pharmacyAdmin/PharmacyPharmacists.vue'
import AddPharmacist from '../views/pharmacyAdmin/AddPharmacist.vue'
import myDermatologistAppointments from '../views/patient/myDermatologistAppointments.vue'
import Complaints from '../views/systemAdmin/Complaints.vue'
import PharmacyMedication from '../views/pharmacyAdmin/PharmacyMedication.vue'
import SupplierReviewsActiveTenders from '../views/supplier/SupplierReviewsActiveTenders.vue'
import MyOffers from '../views/supplier/MyOffers.vue'
import SupplierAddMedication from '../views/supplier/SupplierAddMedication.vue'
import SupplierMyMedications from '../views/supplier/SupplierMyMedications.vue'
import ERecipes from '../views/patient/ERecipes.vue'
import PatientComplaint from '../views/patient/PatientComplaint.vue'
import showMedicationsHome from '../views/home/showMedicationsHome.vue'
import myPharmacistsConsultation from '../views/patient/myPharmacistsConsultation.vue'
import PharmacyAdminMedicationSearch from '../views/pharmacyAdmin/PharmacyAdminMedicationSearch.vue'
import HolidayRequests from '../views/pharmacyAdmin/HolidayRequests.vue'
import ExaminationTerms from '../views/pharmacyAdmin/ExaminationTerms.vue'
import PharmacistMedicationSearch from '../views/pharmacist/PharmacistMedicationSearch.vue'
import DermatologistMedicationSearch from '../views/dermatologist/DermatologistMedicationSearch.vue'
import HolidayRequestsDermatologists from '../views/systemAdmin/HolidayRequestsDermatologists.vue'
import Pharmacists from '../views/home/Pharmacists.vue'
import pharmacyProfilePatient from '../views/patient/pharmacyProfilePatient.vue'
import WorkingHoursDermatologist from '../views/dermatologist/WorkingHoursDermatologist.vue'
import EditOrder from '../views/pharmacyAdmin/EditOrder.vue'
import Dermatologists from '../views/home/Dermatologists.vue'
import MedicationGraphics from '../views/pharmacyAdmin/MedicationGraphics.vue'
import IncomeGraphics from '../views/pharmacyAdmin/IncomeGraphics.vue'
import ExaminationGraphics from '../views/pharmacyAdmin/ExaminationGraphics.vue'
import Mapa from '../views/systemAdmin/Mapa.vue'
import Inquires from '../views/pharmacyAdmin/Inquires.vue'
import UpdatePharmacyProfile from '../views/pharmacyAdmin/UpdatePharmacyProfile.vue'


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
      path: '/noteExamination',
      name: 'NoteAboutExamination',
      component: NoteAboutExamination
    },
    {
      path: '/pharmacistProfile',
      name: 'PharmacistProfile',
      component: PharmacistProfile
    },
    {
      path: '/workingHoursDermatologist',
      name: 'WorkingHoursDermatologist',
      component: WorkingHoursDermatologist
    },
    {
      path: '/schedulingExamination',
      name: 'SchedulingExamination',
      component: SchedulingExamination
    },
    {
      path: '/pharmacistUpdateProfile',
      name: 'PharmacistUpdateProfile',
      component: PharmacistUpdateProfile
    },
    {
      path: '/dermatologistProfile',
      name: 'DermatologistProfile',
      component: DermatologistProfile
    },
    {
      path: '/dermatologistUpdateProfile',
      name: 'DermatologistUpdateProfile',
      component: DermatologistUpdateProfile
    },
    {
      path: '/dermatologistHoliday',
      name: 'CreateRequestHoliday',
      component: CreateRequestHoliday
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
      path: '/dermatologistClients',
      name: 'DermatologistClients',
      component: DermatologistClients
    },
    {
      path: '/workingHoursPharmacist',
      name: 'WorkingHoursPharmacist',
      component: WorkingHoursPharmacist
    },
    {
      path: '/createRequestVacation',
      name: 'CreateRequestVacation',
      component: CreateRequestVacation
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
      path: '/schedulingConsulting',
      name: 'SchedulingConsulting',
      component: SchedulingConsulting
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
      path: '/pharmacyProfile/:id',
      name: 'PharmacyProfile',
      component: PharmacyProfile
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
      path: '/pharmacistConsultation',
      name: 'pharmacistConsultation',
      component: PharmacistConsultation
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
      path: '/myDermatologistAppointments',
      name: 'myDermatologistAppointments',
      component: myDermatologistAppointments
    },
  
{
      path: '/complaints',
      name: 'Complaints',
      component: Complaints
},
    {

      path: '/pharmacyMedications',
      name: 'PharmacyMedication',
      component: PharmacyMedication
    },
    {
      path: '/supplierReviewsActiveTenders',
      name: 'SupplierReviewsActiveTenders',
      component: SupplierReviewsActiveTenders
    },
    {
        path: '/myOffers',
      name: 'MyOffers',
      component: MyOffers
    },
    {
      path: '/supplierMyMedications',
      name: 'SupplierMyMedications',
      component: SupplierMyMedications
    },
    {
      path: '/supplierAddMedication',
      name: 'SupplierAddMedication',
      component: SupplierAddMedication
    },
    {
      path: '/eRecipes',
      name: 'ERecipes',
      component: ERecipes
    },
    {
      path: '/patientComplaint',
      name: 'PatientComplaint',
      component: PatientComplaint
    },
    {
      path: '/showMedicationsHome',
      name: 'showMedicationsHome',
      component: showMedicationsHome
    },
    {
      path: '/myPharmacistsConsultation',
      name: 'myPharmacistsConsultation',
      component: myPharmacistsConsultation
    },
    {
      path: '/pharmacyAdminMedicationSearch',
      name: 'PharmacyAdminMedicationSearch',
      component: PharmacyAdminMedicationSearch
    },
    {
      path: '/pharmacistMedicationSearch',
      name: 'PharmacistMedicationSearch',
      component: PharmacistMedicationSearch
    },
    {
      path: '/dermatologistMedicationSearch',
      name: 'DermatologistMedicationSearch',
      component: DermatologistMedicationSearch
    
    },
    {
      path: '/holidayRequests',
      name: 'HolidayRequests',
      component: HolidayRequests
    },
    {
      path: '/holidayRequestsDermatologists',
      name: 'HolidayRequestsDermatologists',
      component: HolidayRequestsDermatologists
    },
    {
      path: '/pharmacists',
      name: 'Pharmacists',
      component: Pharmacists
    },
    {
      path: '/pharmacyProfilePatient/:id',
      name: 'pharmacyProfilePatient',
      component: pharmacyProfilePatient
      
    },
    {
      path: '/dermatologists',
      name: 'Dermatologists',
      component: Dermatologists
    },
    {
      path: '/examinationTerms',
      name: 'ExaminationTerms',
      component: ExaminationTerms
    },
    {
      path: '/editOrder',
      name: 'EditOrder',
      component: EditOrder
    },
    {
      path: '/medicationGraphics',
      name: 'MedicationGraphics',
      component: MedicationGraphics
    },
    {
      path: '/incomeGraphics',
      name: 'IncomeGraphics',
      component: IncomeGraphics
    },
    {
      path: '/examinationGraphics',
      name: 'ExaminationGraphics',
      component: ExaminationGraphics
    },
    {
      path: '/mapa/:id',
      name: 'Mapa',
      component: Mapa
    },
    {
      path: '/medicationInquires',
      name: 'Inquires',
      component: Inquires
    },
    {
      path: '/updatePharmacyProfile',
      name: 'UpdatePharmacyProfile',
      component: UpdatePharmacyProfile
    },


    
  ]
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
