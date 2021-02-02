<template>
  <div id="registration" style="background-image: url(https://img.freepik.com/free-photo/abstract-blur-defocused-pharmacy-drug-store_1203-9459.jpg?size=626&ext=jpg);background-repeat: no-repeat;
     background-size: 175% 100%;  height: 1500px">
        <div style="background: #0D184F; height: 90px;">
            
            <span style="float: left; margin: 15px;">
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showHomePage">Pharmacies</button>
                   <b class="tab"></b>                
                   
                <button class = "btn btn-link btn-lg" style="float:left;margin-left:20px;" v-on:click = "showMedications">Medications</button>
                  
            </span>
              <span  style="float:right;margin:15px">
                    <button class = "btn btn-warning btn-lg" v-on:click = "showLoginForm">&nbsp;&nbsp;Login&nbsp;&nbsp;</button>

                    <b class="tab"></b>                
                   

                    <button class = "btn btn-warning btn-lg" style="margin-right:20px;" v-on:click = "showRegistrationForm">Register</button>
                </span>

        </div>

            <div style="background: white; height: 60px; margin-top: 20px">
            
           <span  style="float:right;margin:15px">
                    
                   <div class="input-group mb-3">
  <input type="text" v-model="pharmacyName" class="form-control" placeholder="Search by pharmacy name" aria-label="Search by pharmacy name" aria-describedby="basic-addon2">
  <div class="input-group-append">
    <button class="btn btn-outline-secondary" type="button"  v-on:click = "searchName(pharmacyName)" >Search</button>
  </div>
</div>

            
                </span>

                  <span  style="float:right;margin:15px">
                    
                   <div class="input-group mb-3">
  <input type="text" v-model="pharmacyCity" class="form-control" placeholder="Search pharmacy by city" aria-label="Search pharmacy by city" aria-describedby="basic-addon2">
  <div class="input-group-append">
    <button class="btn btn-outline-secondary" type="button"  v-on:click = "searchCity(pharmacyCity)" >Search</button>
  </div>
</div>

            
                </span>
             
            
        </div>


               <h4 style="color: #0D184F;margin:20px">Pharmacies:</h4>


 <div  v-for="pharmacy in this.pharmacies"  v-bind:key="pharmacy.id">
       <div v-if="showTable"  style="background: whitesmoke; border: 3px solid #0D184F; height: 200px; width:1000px; margin-left:300px; margin-top: 20px">
          
 <router-link :to="{ path: '/pharmacyProfile/'+pharmacy.id}" v-slot="{href, navigate}">
<table id="table2" class="table" :href="href" @click="navigate"  elevation="1">
 
    <tbody>
    <tr>
      <th scope="row"></th>
      <td>Pharmacy name</td>
      <td>{{pharmacy.pharmacyName}} </td>
    
    </tr>
    <tr>
      <th scope="row"></th>
      <td>Address</td>
      <td>{{pharmacy.postalCode}} {{pharmacy.country}}, {{pharmacy.street}} {{pharmacy.number}}</td>

    </tr>
    <tr>
      <th scope="row"></th>
      <td>Mark</td>
      <td>{{pharmacy.mark}} </td>
     
    </tr>
     
    
  </tbody>
</table>
        </router-link>
     


           </div>

      </div>     






 <div  v-for="pharmacy1 in this.pharmacies1"  v-bind:key="pharmacy1.id">
       <div id="customers" v-if="showSecondTable"  style="background: whitesmoke; border: 3px solid #0D184F; height: 200px; width:1000px; margin-left:300px; margin-top: 20px">
          
 <router-link :to="{ path: '/pharmacyProfile/'+pharmacy1.id}" v-slot="{href, navigate}">
<table id="table1" class="table" :href="href" @click="navigate"  elevation="1">
 
    <tbody>
    <tr>
      <th scope="row"></th>
      <td>Pharmacy name</td>
      <td>{{pharmacy1.pharmacyName}} </td>
    
    </tr>
    <tr>
      <th scope="row"></th>
      <td>Address</td>
      <td>{{pharmacy1.postalCode}} {{pharmacy1.country}}, {{pharmacy1.street}} {{pharmacy1.number}}</td>

    </tr>
    <tr>
      <th scope="row"></th>
      <td>Mark</td>
      <td>{{pharmacy1.mark}} </td>
     
    </tr>
     
    
  </tbody>
</table>
        </router-link>
     


           </div>

      </div>     




    </div>
</template>



<script>
export default {

  data() {
    return {
       pharmacy : {},
       pharmacies : [],
       pharmacy1: null,
       pharmacies1: [],
       showComplaintForm : false,
       pharmacists : [],
       pharmacist : null,
       dermatologists : [],
       dermatologist : null,
       showPharmacyComplaint : false,
       showPharmacistComplaint : false,
       showDermatologistComplaint : false,
       dermatologistAppointments: [],
       dermatologistAppointment : null,
       pharmacyName: null,
       pharmacyCity: null,
       dermatologistAppointmentsSearch: [],
       showTable: true,
       showSecondTable : false,
       filter: null
       
    }
  },

  methods:{
     searchName: function(pharmacyName){
           
             this.pharmacyName = pharmacyName
               alert(this.pharmacyName)
      this.axios.get('/pharmacy/searchName/'+ this.pharmacyName)
          .then(response => {
              this.showTable = false;
              this.showSecondTable = true;
                this.pharmacies1= response.data;
               console.log(this.pharmacies1);

                if(this.pharmacies1.length == null){
                     this.showSecondTable = false;
                }
              
          })
      },

      searchCity: function(pharmacyCity){
           
             this.pharmacyCity = pharmacyCity
               alert(this.pharmacyCity)
      this.axios.get('/pharmacy/searchCity/'+ this.pharmacyCity)
          .then(response => {
              this.showTable = false;
              this.showSecondTable = true;
                this.pharmacies1= response.data;
               console.log(this.pharmacies1);

                if(this.pharmacies1.length == null){
                     this.showSecondTable = false;
                }
              
          })
      },
    showMedications: function(){
        window.location.href = "/showMedicationsHome";

      },
      showRegistrationForm : function(){
        window.location.href = "/registration";

      },
      showLoginForm: function(){
        window.location.href = "/login";

      },
      showHomePage : function(){
          window.location.href = "/isaHomePage";
      },
        showPharmacy :function (event, pharmacy) {
            this.choosenPharmacy = pharmacy;
           
      },
},
mounted() {
        this.axios.get('/pharmacy/all')
        .then(response => {
                this.pharmacies = response.data;
                console.log(this.admin);
         }).catch(res => {
                alert("NOT OK");
                console.log(res);
        });
        
    }
     
}
</script>

<style>

</style>


  