<template>
<div  v-if="loggedIn"  id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 175%;  height: 2500px">
        <div style="background: #0D184F; height: 90px;">
             <span style="float: left; margin: 15px;">
                              <b-dropdown id="ddCommodity" name="ddCommodity" text="My profile" 
                                              class = "btn btn-link btn-lg">
                                    <b-dropdown-item href = "/pharmacyAdminProfile">Profile</b-dropdown-item>
                                    <b-dropdown-item href = "/phAdminProfileUpdate">Update profile</b-dropdown-item>      
                                </b-dropdown>        
                       <strong class="tab"></strong>  

                                    <router-link :to="{ path: '/pharmacyProfile/'+pharmacy.id}" v-slot="{href, navigate}">
                                                <button class = "btn btn-secondary" :href="href" @click="navigate"  elevation="1">My pharmacy profile</button>
                                    </router-link>
                   
                          <strong class="tab"></strong>  

                                  <b-dropdown id="ddCommodity" name="ddCommodity" text="Pharmacists" 
                                                class = "btn btn-link btn-lg">
                                      <b-dropdown-item href = "/pharmacyPharmacists">Our pharmacists</b-dropdown-item>
                                      <b-dropdown-item href = "/addPharmacist">Add new pharmacist</b-dropdown-item>      
                                  </b-dropdown> 
                          <strong class="tab"></strong>  
                                  <b-dropdown id="ddCommodity" name="ddCommodity" text="Dermatologists" 
                                                  class = "btn btn-link btn-lg">
                                        <b-dropdown-item href = "/pharmacyDermatologists">Our dermatologists(Add new)</b-dropdown-item>
                                        <b-dropdown-item href = "/examinationTerms">Examination terms</b-dropdown-item>      
                                    </b-dropdown>                 
                            <strong class="tab"></strong>  
                            <a   class = "btn btn-secondary" href = "/pharmacyMedications">Medications</a>
                            <strong class="tab"></strong>  
                            <a   class = "btn btn-secondary" href = "/pharmacyAdminMedicationSearch">Medications in system</a>
                            <strong class="tab"></strong>  
                            <a  class = "btn btn-secondary" href = "/actionsAndBenefits">Actions and benefits</a>
                            <strong class="tab"></strong>  
                            <b-dropdown id="ddCommodity" name="ddCommodity" text="Orders" 
                                                  class = "btn btn-link btn-lg">
                                        <b-dropdown-item href = "/order">Preview orders and offers(Add new)</b-dropdown-item>
                                        <b-dropdown-item href = "/editOrder">Edit/remove order</b-dropdown-item>      
                                    </b-dropdown>                             
                                    <strong class="tab"></strong>  
                            <a   class = "btn btn-secondary" href="/holidayRequests">Holiday/absence requests</a>
                             <strong class="tab"></strong>  
                            <b-dropdown id="ddCommodity" name="ddCommodity" text="Graphical reviews" 
                                                  class = "btn btn-link btn-lg">
                                        <b-dropdown-item href = "/examinationGraphics">examinations review</b-dropdown-item>
                                        <b-dropdown-item href = "/medicationGraphics">Medication consumption</b-dropdown-item> 
                                        <b-dropdown-item href = "/incomeGraphics">Income</b-dropdown-item>      
                                    </b-dropdown>     
            </span>
              <span  style="float:right;margin:15px">
                   
                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                
                </span>
        </div>
  <section class="container">
    <div class="columns">
     
      <div class="column">
            <h3 style="color: #0D184F">Half year review of your pharmacy income</h3>

   <chartjs-line :labels="labels" :data="dataset" :bind="true"></chartjs-line>

      </div>
    </div>
  </section>
  </div>
</template>

<script>
  export default {
    name: 'VueCharts',
    data () {
      return {
        labels: ['January', 'February', 'March', 'April', 'May', 'June'],
        dataset: [],
        dataentry : "",
        datalabel : "",
        year : {},
        admin : {},
        pharmacy : {},
        loggedIn : false
        
      }
    },
    methods: {
  addData () {
    this.dataset.push(this.dataentry)
    this.labels.push(this.datalabel)
    this.datalabel = ''
    this.dataentry = ''
  },
   logOut : function(){
          localStorage.removeItem('token');
          window.location.href = "/login";
      },
    },
   mounted() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacyAdmin/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.admin=response.data;
               this.loggedIn = true;
        
         }).catch(res => {
                       alert("NOT OK");
                        console.log(res);
                 });
        this.axios.get('/pharmacyAdmin/myPharmacy',{ 
                    headers: {
                        'Authorization': 'Bearer ' + token,
                    }
                    }).then(response => {
                            this.pharmacy = response.data;
                            console.log(this.pharmacyAdminPharmacy);
                    }).catch(res => {
                            alert("NOT OK");
                            console.log(res);
                    });
    
        this.axios.get('/graphical/income',{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
          }}).then(response => {
           var january = response.data.january;
           this.dataset.push(january);

           var february = response.data.february;
           this.dataset.push(february);

           var march = response.data.march;
           this.dataset.push(march);

           var april = response.data.april;
           this.dataset.push(april);

           var may = response.data.may;
           this.dataset.push(may);

           var june = response.data.june;
           this.dataset.push(june);
           
          
         
         }).catch(res => {
                alert("You are not logged in.");
                console.log(res);
        });

        
    },

  }
</script>